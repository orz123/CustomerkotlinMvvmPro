package com.orz.kotlin_mvvm_demo.base

import android.os.Bundle
import android.view.View
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import org.jetbrains.anko.find

/**
 * @author: orz on 2018/10/18
 *  * TitLe保持显示
 * 优雅地处理加载中，重试，无数据等Fragment
 */
abstract class KeepTitleLoadSirFragment: BaseFragment() {
    protected  var mLoadService: LoadService<*>?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootView?.let {
            val contentView = it.find<View>(contentResId)
            mLoadService = LoadSir.getDefault().register(contentView) { v -> onNetReload(v) }
        }
    }

    abstract val contentResId: Int
    abstract fun onNetReload(v: View?)
}