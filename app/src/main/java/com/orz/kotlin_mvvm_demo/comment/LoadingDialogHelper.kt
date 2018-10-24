package com.orz.kotlin_mvvm_demo.comment

import android.content.Context
import com.orz.kotlin_mvvm_demo.R
import com.orz.kotlin_mvvm_demo.widget.dialog.CustomLoadingDialog

/**
 * @author: orz on 2018/10/24
 * 正在加载Dialog帮助类
 */
object LoadingDialogHelper {
    private var mDialog: CustomLoadingDialog? = null
    /**
     * 获取Dialog
     */
    fun init(context: Context,
                    message: Int= R.string.ui_loading): LoadingDialogHelper {
        if (mDialog == null) {
            mDialog = CustomLoadingDialog(context,message)
        }
        return LoadingDialogHelper
    }

    /**
     * 显示Dialog
     */
    fun showDialog() {
        mDialog?.let {
            it.show()
        }

    }

    /**
     * 隐藏Dialog
     */
    fun hideDialog() {
        if (mDialog != null) {
            mDialog?.hide()
        }
    }

    /**
     * 关闭Dialog
     */
    fun dismissDialog() {
        if (mDialog != null) {
            mDialog?.dismiss()
            mDialog = null
        }
    }
}