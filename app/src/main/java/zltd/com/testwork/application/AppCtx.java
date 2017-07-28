package zltd.com.testwork.application;

import android.app.Application;

import zltd.com.testwork.utils.DbUtils;

/**
 * Created by q on 2017/6/27.
 */

public class AppCtx extends Application {
    //	AppCtx
    private static AppCtx mAppCtx;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppCtx = this;
        DbUtils.setupDatabase();
    }

    public static AppCtx getAppCtx() {
        return mAppCtx;
    }
}
