package zltd.com.testwork.db;

import com.j256.ormlite.dao.Dao;
import zltd.com.testwork.bean.BaseVO;
import zltd.com.testwork.bean.Student;

import java.sql.SQLException;
import java.util.List;

public class BaseTBManager<T> {
    public static BaseTBManager mManager;
    public Dao<T, ?> mDao;
    public Class cls;

    public <T> BaseTBManager(Class clz) {
//		this.cls=Student.class;
        this.cls = clz;
        try {
            mDao = SQLiteHelper.getInstance().getDao(cls);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static BaseTBManager getInstance(Class clazz) {
        if (mManager == null) {
            mManager = new BaseTBManager(clazz);
        }
        return mManager;
    }

    public void insert(T vo) {
        try {
            mDao.create(vo);
        } catch (Exception e) {

        }
    }

    public void delete() {
        try {
            mDao = SQLiteHelper.getInstance(
            ).getDao(cls);
            mDao.deleteBuilder().delete();
        } catch (Exception e) {

        }
    }

    //添加列表
    public void insertList(List<T> list) {
        try {
            for (T vo : list) {
                insert(vo);
            }
        } catch (Exception e) {
        }
    }
}
