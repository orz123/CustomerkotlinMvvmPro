package com.orz.kotlin_mvvm_demo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir


/**
 * @author: orz on 2018/10/18
 * TitLe不显示
 * 优雅地处理加载中，重试，无数据等Fragment
 */
abstract class DefaultLoadSirFragment: BaseFragment() {

    protected  var mLoadService: LoadService<*>?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        rootView = inflater.inflate(layoutResId, null, false)
        mLoadService = LoadSir.getDefault().register(rootView) { v -> onNetReload(v) }
        return mLoadService?.loadLayout?:rootView
    }

    abstract fun onNetReload(v: View?)

}