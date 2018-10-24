package com.orz.kotlin_mvvm_demo.core.view.main.fragment

import android.os.Handler
import android.view.View
import android.widget.TextView
import com.orz.kotlin_mvvm_demo.R
import com.orz.kotlin_mvvm_demo.base.KeepTitleLoadSirFragment
import com.orz.kotlin_mvvm_demo.callback.loadsir.ErrorCallback
import com.orz.kotlin_mvvm_demo.callback.loadsir.LoadingCallback
import com.orz.kotlin_mvvm_demo.di.Injectable
import com.orz.kotlin_mvvm_demo.util.NetworkUtils
import org.jetbrains.anko.support.v4.find

/**
 * Create by orz on 2018/9/19
 * 首页Fragment
 */
class HomeFragment :KeepTitleLoadSirFragment(), Injectable {

    private lateinit var title:TextView


    override val layoutResId: Int by lazy { R.layout.fragment_home }
    override val contentResId: Int
        get() = R.id.fl_content_home


    override fun configViews() {
        title = find(R.id.common_title)
        title.text = getString(R.string.ui_main_tab_home)
    }

    private fun testAsyncHttp() {
        Handler().postDelayed({
            if (activity?.let { NetworkUtils.isNetworkAvailable(it) }!!){
                mLoadService?.showSuccess()
            }else{
                mLoadService?.showCallback(ErrorCallback::class.java)
            }

        },2000)
    }

    override fun lazyLoad() {
        testAsyncHttp()
    }



    override fun onNetReload(v: View?) {
        mLoadService?.showCallback(LoadingCallback::class.java)
        testAsyncHttp()
    }
}