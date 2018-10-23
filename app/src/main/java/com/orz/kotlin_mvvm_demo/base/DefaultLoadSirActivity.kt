package com.orz.kotlin_mvvm_demo.base

import android.os.Bundle
import android.view.View
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir

/**
 * @author: orz on 2018/10/18
 * TitLe不显示
 * 优雅地处理加载中，重试，无数据等Activity
 */
abstract class DefaultLoadSirActivity: BaseActivity() {

    protected  var mLoadService: LoadService<*>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootView?.let {
            mLoadService = LoadSir.getDefault().register(it) { v -> onNetReload(v) }
        }

    }


    abstract fun onNetReload(v: View?)
}