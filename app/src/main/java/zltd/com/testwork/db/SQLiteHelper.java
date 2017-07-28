package zltd.com.testwork.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import zltd.com.testwork.application.AppCtx;
import zltd.com.testwork.bean.Person;
import zltd.com.testwork.bean.Student;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by q on 2017/6/27.
 */

public class SQLiteHelper extends OrmLiteSqliteOpenHelper {

    private static final String TABLE_NAME = "test.db";
    private static SQLiteHelper sInstance;


    private Map<String, Dao> daos = new HashMap<String, Dao>();


    private SQLiteHelper(Context context) {
        super(context, TABLE_NAME, null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        // TODO Auto-generated method stub
        try {
            TableUtils.createTableIfNotExists(connectionSource, Person.class);
            TableUtils.createTableIfNotExists(connectionSource, Student.class);
        } catch (SQLException e) {
            /* TODO Auto-generated catch block */
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int arg2, int arg3) {
        // TODO Auto-generated method stub
        try {
            TableUtils.createTableIfNotExists(connectionSource, Person.class);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public synchronized Dao<?, ?> getNewDao(Class<?> clazz) throws SQLException {
        Dao<?, ?> dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className)) {
            dao = daos.get(className);
        }
        if (dao == null) {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();

        for (String key : daos.keySet()) {
            @SuppressWarnings("unused")
            Dao<?, ?> dao = daos.get(key);
            dao = null;
        }
    }

    public static SQLiteHelper getInstance() {
        if (sInstance == null) {
            sInstance = new SQLiteHelper(AppCtx.getAppCtx());
        }
        return sInstance;
    }
}
