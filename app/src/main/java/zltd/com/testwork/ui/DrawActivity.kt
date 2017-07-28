package zltd.com.testwork.ui

import android.app.Activity
import android.os.Bundle
import zltd.com.testwork.DrawView
import zltd.com.testwork.R

class DrawActivity : Activity() {

    private var mDrawView: DrawView? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.draw)
        init()
    }

    private fun init() {
        //	        LinearLayout layout=(LinearLayout) findViewById(R.id.root);
        //	        LogUtils.i("debug","draw");
        //	        final DrawView view=new DrawView(this,R.attr.DrawView, null);
        ////	        view.setMinimumHeight(500);
        ////	        view.setMinimumWidth(300);
        //	        view.setMinimumWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //	        view.setMinimumHeight(ViewGroup.LayoutParamsO.MATCH_PARENT);
        //	        //通知view组件重绘
        //	        view.invalidate();
        //	        layout.addView(view);

        mDrawView = findViewById<DrawView>(R.id.circleView) as DrawView
        mDrawView!!.setPercent((Math.random() * 100).toInt())
    }

}
