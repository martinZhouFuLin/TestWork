package zltd.com.testwork.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import zltd.com.testwork.BarcodeFactoty
import zltd.com.testwork.BaseScanActivity
import zltd.com.testwork.utils.InputViewManager
import zltd.com.testwork.R
import zltd.com.testwork.annotation.ContentView
import zltd.com.testwork.annotation.ViewInject
import zltd.com.testwork.bean.Person
import zltd.com.testwork.bean.Student
import zltd.com.testwork.db.BaseTBManager

/**
 *
 * @author zhoufulin
 * @time 2016/05/27
 * N2S000扫描demo
 */

class TestActivity : BaseScanActivity(), View.OnClickListener {

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
    }

    override
    protected fun onStart() {
        super.onStart()
    }

    override
    protected fun onResume() {
        super.onResume()
    }

    override
    protected fun onStop() {
        super.onStop()
    }

    override
    protected fun onDestroy() {
        super.onDestroy()
    }

    private var textView: TextView? = null
    private var mWayBilledit: EditText? = null
    private var mBackBilledit: EditText? = null
    private var mSiteedit: EditText? = null
    private var mBigBagedit: EditText? = null
    private var list = listOf<EditText?>()
    fun initView() {
        setContentView(R.layout.activity_test)
        textView  = findViewById<TextView>(R.id.tv1) as TextView
        mWayBilledit = findViewById<EditText>(R.id.waybillno) as EditText
        mBackBilledit = findViewById<EditText>(R.id.backno) as EditText
        mSiteedit = findViewById<EditText>(R.id.siteno) as EditText
        mBigBagedit = findViewById<EditText>(R.id.bigbagno) as EditText
        list = listOf(mWayBilledit, mBackBilledit, mBigBagedit, mSiteedit)
        InputViewManager.setmViews(list)
    }

    fun initData() {
        var person = Person()
        person.age = "20"
        person.name = "lilei"
        person.weight = "60";

        var student = Student()
        student.age = "20"
        student.name = "lilei"
        student.weight = "60";

        BaseTBManager.getInstance(person.javaClass).insert(person)
        BaseTBManager.getInstance(student.javaClass).insert(student)

    }

    @Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		// TODO Auto-generated method stub
//		if((keyCode != 0x4) && (keyCode == 0x6f)
//		 || event.getRepeatCount() == 0) {
//			 Toast.makeText(this,"oooooooo",500).show();
//		 }
//		 return true;
//	}


    override
    fun onClick(v: View?) {
// 按钮事件
        when (v!!.getId()) {
//            R.id.btn_1 ->
//// 单扫
//                mScannerManager.setScanMode(ScannerManager.SCAN_SINGLE_MODE)
//                R.id.btn_2 ->
//// 连扫
//                mScannerManager.setScanMode(ScannerManager.SCAN_CONTINUOUS_MODE)
////			mWayBilledit!!.setText("11")
                R.id.btn_3 -> {
                    // 跳转
                val intent = Intent(this@TestActivity, WebActivity::class.java)
                startActivity(intent)
            }
            else -> {
            }
        }
    }

    override
    fun getScanData(barcode: String?) {
        BarcodeFactoty.getInstance(this@TestActivity).checkBarcode(barcode)
    }
}