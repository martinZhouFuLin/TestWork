package zltd.com.testwork.ui;

import android.os.Bundle;
import android.view.View;

import zltd.com.testwork.BaseScanActivity;
import zltd.com.testwork.bean.Person;
import zltd.com.testwork.db.BaseTBManager;
import zltd.com.testwork.utils.LogUtils;
import zltd.com.testwork.R;
import zltd.com.testwork.utils.TimeUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by q on 2017/6/28.
 */

public class DemoActivity extends BaseScanActivity {
    private List<String> list = new ArrayList<String>();
    private TimeUtil timeUtil;
    String timeString = "";
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        timeUtil = new TimeUtil();
        for (int i = 0; i < 50000; i++) {
            list.add("" + i);
        }
        Person person = new Person();
        person.setAge("20");
        person.setName("hanmeimei");
        person.setWeight("50");
        BaseTBManager.getInstance(Person.class).insert(person);
    }

    public void optimalizeFor(List<String> list) {
        long time1 = System.currentTimeMillis();
        int j = 0;
        for (int i = 0, len = list.size(); i < len; i++) {
            j++;
            LogUtils.Companion.i("jj");
        }
        long time2 = System.currentTimeMillis();
        LogUtils.Companion.i("时差1==" + list.size() + "==" + (time2 - time1));
    }

    public void optimalizeFor1(List<String> list) {
        long time1 = System.currentTimeMillis();
        int j = 0;
        for (int i = 0; i < list.size(); i++) {
            try {
                j++;
                LogUtils.Companion.i("jj");
            } catch (Exception e) {

            }

        }
        long time2 = System.currentTimeMillis();
        LogUtils.Companion.i("时差2==" + list.size() + "==" + (time2 - time1));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

//    @Override
//    protected void onScanned(String barcode) {
//        super.onScanned(barcode);
//    }

    public void onClick(View v) {
        // 按钮事件
        switch (v.getId()) {
            case R.id.btn_1:
                optimalizeFor(list);
                // 单扫
//                mScannerManager.setScanMode(ScannerManager.SCAN_SINGLE_MODE);
                break;
            case R.id.btn_2:
                optimalizeFor1(list);
                // 连扫
//                mScannerManager.setScanMode(ScannerManager.SCAN_CONTINUOUS_MODE);
                break;
            case R.id.btn_3:
                // 跳转
//                Intent intent = new Intent(TestActivity.this,WebActivity.class);
//                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
