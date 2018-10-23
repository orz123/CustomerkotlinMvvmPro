package com.orz.kotlin_mvvm_demo.callback.loadsir

import com.kingja.loadsir.callback.Callback
import com.orz.kotlin_mvvm_demo.R

/**
 * @author: orz on 2018/10/18
 */
class ErrorCallback: Callback() {
    override fun onCreateView(): Int {
        return R.layout.common_error_view
    }
}