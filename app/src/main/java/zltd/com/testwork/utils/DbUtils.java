package zltd.com.testwork.utils;

import android.database.sqlite.SQLiteDatabase;

import zltd.com.testwork.application.AppCtx;
import zltd.com.testwork.bean.DaoMaster;
import zltd.com.testwork.bean.DaoSession;

/**
 * Created by q on 2017/7/28.
 */

public class DbUtils {


    private static DaoSession daoSession;
    /**
     * 配置数据库
     */
    public static void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(AppCtx.getAppCtx(), "shop.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }
}
