package com.orz.kotlin_mvvm_demo.comment

import android.app.ProgressDialog
import android.content.Context
import android.support.v4.content.ContextCompat
import com.github.ybq.android.spinkit.style.FadingCircle
import com.orz.kotlin_mvvm_demo.R


/**
 * @author: orz on 2018/10/24
 * 水平进度条Dialog
 */
object ProgressDialogHelper {
    private var mDialog: ProgressDialog? = null

    /**
     * 获取进度条
     */
    fun init(context: Context): ProgressDialogHelper {
        if (mDialog == null) {
            mDialog = ProgressDialog(context)
        }
        return ProgressDialogHelper
    }

    /**
     * 显示进度条
     */
    fun showDialog(message: Int= R.string.ui_committing) {
        mDialog?.let {
            it.setMessage(it.context.getString(message))
            val mCircleDrawable  = FadingCircle()
            mCircleDrawable.setBounds(0, 0, 100, 100)
            mCircleDrawable.color = ContextCompat.getColor(it.context,R.color.colorAccent)
            it.setIndeterminateDrawable(mCircleDrawable)
            it.setCancelable(false)
            it.show()
        }
    }

    /**
     * 隐藏进度条
     */
    fun hideDialog() {
        if (mDialog != null) {
            mDialog?.hide()
        }
    }

    /**
     * 关闭进度条
     */
    fun dismissDialog() {
        if (mDialog != null) {
            mDialog?.dismiss()
            mDialog = null
        }
    }
}