package zltd.com.testwork.utils;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import zltd.com.testwork.Interface.DataScanner;

/**
 * Created by q on 2017/7/24.
 */

public class EventUtils {
    private List<View> mViews = new ArrayList<View>();
    private static EventUtils mEventUtils;

    public static EventUtils getInstance() {
        if (mEventUtils == null) {
            mEventUtils = new EventUtils();
        }
        return mEventUtils;
    }

    public void registScanner(DataScanner mDataScanner) {
        ScanUtils.getInstance().addScanner(mDataScanner);
    }

    public void removeScanner(DataScanner mDataScanner) {
        ScanUtils.getInstance().removeScanner(mDataScanner);
    }
}