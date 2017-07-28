package zltd.com.testwork.utils

import android.widget.EditText

class InputViewManager {

    companion object {
        //        fun create(): val instance= InputViewManager()
        private var mViews = listOf<EditText?>()

        fun getmViews(): List<EditText?> {
            return mViews
        }

        fun setmViews(mEditViews: List<EditText?>) {
            mViews = mEditViews
        }
    }
}
