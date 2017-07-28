package zltd.com.testwork;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import zltd.com.testwork.bean.Shop;
import zltd.com.testwork.utils.DbUtils;
import zltd.com.testwork.utils.EventUtils;
import zltd.com.testwork.utils.LogUtils;
import zltd.com.testwork.utils.ScanUtils;
import zltd.com.testwork.utils.SoundUtils;

import zltd.com.testwork.Interface.DataScanner;

public class BaseScanActivity extends Activity implements DataScanner {

    Handler handler = new Handler();
//    protected ScannerManager mScannerManager;
    protected SoundUtils mSoundUtils;
    private EventUtils mEventUtis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        initSound();
        EventUtils.getInstance().registScanner(this);
//        mEventUtis=EventUtils.getInstance();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        // 移除监听
//        if (mScannerManager != null) {
//            mScannerManager.removeScannerStatusListener(mIScannerStatusListener);
//        }
//		mScannerManager.scannerEnable(false);
//		mScannerManager = null;
        ScanUtils.getInstance().stopScan();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
//        initScanner();
        ScanUtils.getInstance().scanInit();
        Shop mShop=new Shop();
        mShop.setName("ssss");
        try {
            LogUtils.Companion.i("错误22qs="+111222);
            DbUtils.getDaoInstant().getShopDao().save(mShop);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.Companion.i("错误="+e.getMessage());
        }
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }

    private void initSound() {
        mSoundUtils = SoundUtils.getInstance();
        mSoundUtils.init(this);
    }

    @Override
    public void getScanData(String barcode) {
//        LogUtils.Companion.i("结果2=="+barcode);
//        Toast.makeText(this, "条码:" + barcode, Toast.LENGTH_SHORT).show();
    }

}