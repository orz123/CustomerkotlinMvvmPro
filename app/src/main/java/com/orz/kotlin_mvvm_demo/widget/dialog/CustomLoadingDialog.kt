package com.orz.kotlin_mvvm_demo.widget.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import com.orz.kotlin_mvvm_demo.R
import kotlinx.android.synthetic.main.custom_dialog_loading.*


/**
 * @author: orz on 2018/10/24
 * 正在加载dialog
 */
class CustomLoadingDialog(context: Context,
                          private val message: Int,
                          themeResId: Int = R.style.LoadingDialog,
                          private val cancelable: Boolean = false) : Dialog(context,themeResId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        setContentView(R.layout.custom_dialog_loading)
        // 设置窗口大小
        val windowManager = window?.windowManager
        val screenWidth = windowManager?.defaultDisplay?.width
        val attributes = window?.attributes
        // 设置窗口背景透明度
        attributes?.alpha = 0.6f
        // 设置窗口宽高为屏幕的三分之一（为了更好地适配，请别直接写死）
        attributes?.width = screenWidth?.div(3)
        attributes?.height = attributes?.width
        window?.attributes = attributes
        setCancelable(cancelable)
        tv_loading.text = context.getString(message)

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            // 屏蔽返回键
            return cancelable
        }
        return super.onKeyDown(keyCode, event)
    }
}