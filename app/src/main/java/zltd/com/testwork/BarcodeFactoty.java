package zltd.com.testwork;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import zltd.com.testwork.utils.InputViewManager;
import zltd.com.testwork.utils.LogUtils;
import zltd.com.testwork.utils.RegexUtils;
import zltd.com.testwork.utils.SoundUtils;

import zltd.com.testwork.R;

public class BarcodeFactoty {
    private static Context mContext;

    public static BarcodeFactoty getInstance(Context context) {
        mContext = context;
        return new BarcodeFactoty();
    }

    public void checkBarcode(String barcode) {
        String result = "";

        switch (barcode.length()) {
            case 4:
                Toast.makeText(mContext, "长度4:" + barcode.length(), Toast.LENGTH_SHORT).show();
                SoundUtils.getInstance().warn();
                break;
            case 5:
                Toast.makeText(mContext, "长度5:" + barcode.length(), Toast.LENGTH_SHORT).show();
                SoundUtils.getInstance().warn();
                break;
            case 6:
                Toast.makeText(mContext, "长度6:" + barcode.length(), Toast.LENGTH_SHORT).show();
                setContent(R.id.siteno, barcode);
                break;
            case 8:
                Toast.makeText(mContext, "长度8:" + barcode.length(), Toast.LENGTH_SHORT).show();
                SoundUtils.getInstance().warn();
                break;
            case 9:
                Toast.makeText(mContext, "长度9:" + barcode.length(), Toast.LENGTH_SHORT).show();
                SoundUtils.getInstance().warn();
                break;
            case 10:
                Toast.makeText(mContext, "长度10:" + barcode.length(), Toast.LENGTH_SHORT).show();
                SoundUtils.getInstance().warn();
                break;
            case 11:
                Toast.makeText(mContext, "长度11:" + barcode.length(), Toast.LENGTH_SHORT).show();
                SoundUtils.getInstance().warn();
                break;
            case 12:
                Toast.makeText(mContext, "长度12:" + barcode.length(), Toast.LENGTH_SHORT).show();
                SoundUtils.getInstance().warn();
                break;
            case 13:
                // Toast.makeText(mContext, "长度13:" +RegexUtils.checkWayBillNo(barcode), Toast.LENGTH_SHORT).show();
                if (setContent(R.id.backno, barcode)) {
                    return;
                }
                if (setContent(R.id.bigbagno, barcode)) {
                    return;
                }
                setContent(R.id.waybillno, barcode);
                break;
            case 18:
                if (setContent(R.id.bigbagno, barcode)) {
                    return;
                }
                Toast.makeText(mContext, "长度18:" + RegexUtils.checkWayBillNo(barcode), Toast.LENGTH_SHORT).show();
                setContent(R.id.waybillno, barcode);
                break;
            case 24:
                if (setContent(R.id.bigbagno, barcode)) {
                    return;
                }
                Toast.makeText(mContext, "长度24:" + RegexUtils.checkWayBillNo(barcode), Toast.LENGTH_SHORT).show();
                setContent(R.id.waybillno, barcode);
                break;
            default:

                break;
        }
        // return result;
    }

    private void saveData() {
        Boolean result = true;
        for (View view : InputViewManager.Companion.getmViews()) {
            // if(RegexUtils.check)
            switch (view.getId()) {
                case R.id.waybillno:
                    result = RegexUtils.checkWayBillNo(((EditText) view).getText().toString());
                    if (!result) {
                        Toast.makeText(mContext, "单号错误", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.siteno:
                    if (result) {
                        result = RegexUtils.checkSiteNo(((EditText) view).getText().toString());
                        if (!result) {
                            Toast.makeText(mContext, "站点错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case R.id.backno:
                    if (result) {
                        result = RegexUtils.checkBackNo(((EditText) view).getText().toString());
                        if (!result) {
                            Toast.makeText(mContext, "回单错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case R.id.bigbagno:
                    if (result) {
                        result = RegexUtils.checkBigBagNo(((EditText) view).getText().toString());
                        if (!result) {
                            Toast.makeText(mContext, "大包号错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        if (result) {
            Toast.makeText(mContext, "全部正确", Toast.LENGTH_SHORT).show();
            SoundUtils.getInstance().success();
        } else {
            SoundUtils.getInstance().warn();
        }
        // return false;
    }

    public EditText checkViews(int viewId) {
        View mView = null;
        for (View view : InputViewManager.Companion.getmViews()) {
            if (view.getId() == viewId) {
                mView = view;
                return (EditText) mView;
            }
        }
        LogUtils.Companion.i("没进来了");
        SoundUtils.getInstance().warn();
        return (EditText) mView;
    }

    public boolean setContent(int viewId, String barcode) {
        boolean result = false;
        if (checkViews(viewId) != null) {
            switch (viewId) {
                case R.id.bigbagno:
                    if (RegexUtils.checkBigBagNo(barcode)) {
                        checkViews(viewId).setText(barcode);
                        SoundUtils.getInstance().success();
                        result = true;
                    }
                    break;
                case R.id.backno:
                    if (RegexUtils.checkBackNo(barcode)) {
                        checkViews(viewId).setText(barcode);
                        SoundUtils.getInstance().success();
                        result = true;
                    }
                    break;

                case R.id.waybillno:
                    checkViews(viewId).setText(barcode);
//				SoundUtils.getInstance().success();
                    result = true;
                    saveData();
                    break;
                default:
                    checkViews(viewId).setText(barcode);
                    SoundUtils.getInstance().success();
                    result = true;
                    break;
            }
        }

        return result;
    }
}
