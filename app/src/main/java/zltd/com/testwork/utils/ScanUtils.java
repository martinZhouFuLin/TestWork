package zltd.com.testwork.utils;

import android.content.Context;
import android.os.Handler;

import com.zltd.industry.ScannerManager;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import zltd.com.testwork.Interface.DataMeta;
import zltd.com.testwork.Interface.DataScanner;

/**
 * Created by q on 2017/7/25.
 */

public class ScanUtils implements DataMeta {
    protected ScannerManager mScannerManager;
    private static ScanUtils mScanUtils;
    private Handler handler = new Handler();
    private Context mContext;
    private List<DataScanner> list = new ArrayList<DataScanner>();

    public static ScanUtils getInstance() {
        if (mScanUtils == null) {
            mScanUtils = new ScanUtils();
        }
        return mScanUtils;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public void scanInit() {
        try {
            // 初始化扫描
            mScannerManager = ScannerManager.getInstance();
            mScannerManager.scannerEnable(true);// 扫描可用   true:switch on barcode scanner;
//			                                               false: switch off barcode scanner;


            mScannerManager.setScanMode(ScannerManager.SCAN_SINGLE_MODE);// 单扫
//			 mScannerManager.setScanMode(ScannerManager.SCAN_CONTINUOUS_MODE);//连扫


            mScannerManager.setDataTransferType(ScannerManager.TRANSFER_BY_API);// API获取扫描数据
            //mScannerManager.setDataTransferType(ScannerManager.TRANSFER_BY_EDITTEXT);// 填充文本编辑框
            //mScannerManager.setDataTransferType(ScannerManager.TRANSFER_BY_KEY);// 转换为按键发送


            // 注册监听
            mScannerManager.addScannerStatusListener(mIScannerStatusListener);
        } catch (Exception e) {
        }
    }

    public void stopScan() {
        if (mScannerManager != null) {
            mScannerManager.removeScannerStatusListener(mIScannerStatusListener);
        }
    }

    /**
     * 扫描开关
     *
     */
    public void scanSwitch(boolean scanSwitch) {

        // TODO Auto-generated method stub
        mScannerManager.scannerEnable(scanSwitch);
    }

    /**
     * 扫描状态
     *
     */
    public boolean getScanenabe() {
        // TODO Auto-generated method stub
        return mScannerManager.getScannerEnable();
    }

    /**
     * 连扫控制
     */
    public void continuousScanMode(boolean continuousScan) {
        // TODO Auto-generated method stub
        if (continuousScan && mScannerManager.getScanMode() == Constants.SINGLE_SCAN_MODE) {
            mScannerManager.setScanMode(ScannerManager.SCAN_CONTINUOUS_MODE);
        } else if (!continuousScan && mScannerManager.getScanMode() == Constants.CONTINUOUS_SCAN_MODE) {
            mScannerManager.setScanMode(ScannerManager.SCAN_SINGLE_MODE);
        }
    }
    /**
     * 传输方式控制
     *
     */
    public void switchTransferType(int transferType) {
        if (transferType==ScannerManager.TRANSFER_BY_API||transferType==ScannerManager.TRANSFER_BY_EDITTEXT||transferType==ScannerManager.TRANSFER_BY_KEY) {
            mScannerManager.setDataTransferType(transferType);
        }else{
            mScannerManager.setDataTransferType(ScannerManager.TRANSFER_BY_API);
        }
    }
    private ScannerManager.IScannerStatusListener mIScannerStatusListener = new ScannerManager.IScannerStatusListener() {

        @Override
        public void onScannerStatusChanage(int paramInt) {

            System.out.println("onScannerStatusChanage-------" + paramInt);
        }

        @Override
        public void onScannerResultChanage(final byte[] paramArrayOfByte) {
//			mSoundUtils.success();
            handler.post(new Runnable() {

                @Override
                public void run() {
                    String s = null;
                    try {
                        s = new String(paramArrayOfByte, "UTF-8");
                    } catch (UnsupportedEncodingException ex) {
                        ex.printStackTrace();
                    }
                    if (s != null) {
                        scanData(s);
                        LogUtils.Companion.i("结果==" + s);
//						textView.setText(textView.getText().toString() + "\r\n" + s);
//						BarcodeFactoty.getInstance(TestActivity.this).checkBarcode(s);
//                        onScanned(s);
//						textView.setText(s);/
                    }
                }
            });
        }
    };

    @Override
    public void addScanner(DataScanner mDataScanner) {
        list.add(mDataScanner);
    }

    @Override
    public void removeScanner(DataScanner mDataScanner) {
        list.remove(mDataScanner);
    }

    @Override
    public void scanData(String barcode) {
        for (DataScanner mDataScanner : list) {
            mDataScanner.getScanData(barcode);
        }
    }
}
