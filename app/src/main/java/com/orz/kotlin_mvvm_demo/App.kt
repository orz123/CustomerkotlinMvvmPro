package com.orz.kotlin_mvvm_demo

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.kingja.loadsir.core.LoadSir
import com.orz.kotlin_mvvm_demo.callback.loadsir.EmptyCallback
import com.orz.kotlin_mvvm_demo.callback.loadsir.ErrorCallback
import com.orz.kotlin_mvvm_demo.callback.loadsir.LoadingCallback
import com.orz.kotlin_mvvm_demo.comment.SmartRefreshHelper
import com.orz.kotlin_mvvm_demo.config.URL
import com.orz.kotlin_mvvm_demo.di.AppInjector
import com.orz.kotlin_mvvm_demo.network.HttpHelper
import com.orz.kotlin_mvvm_demo.util.SPFUtil
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Create by orz on 2018/9/12
 */

class App :Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

     override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        asyncInit()

    }

    private fun asyncInit() {
        mInstance = this
        HttpHelper.Builder()
                .initOkHttp()
                .createRetrofit(URL.BASE_URL)
                .build()
        AppInjector().init(this)
        SmartRefreshHelper.setDefaultConfig()
        initPrefs()
        errorHandlerInit()
    }

    private fun errorHandlerInit() {
       LoadSir.beginBuilder()
                .addCallback(ErrorCallback())
                .addCallback(EmptyCallback())
                .addCallback(LoadingCallback())
                .setDefaultCallback(LoadingCallback::class.java)
                .commit()
    }

    /**
     * 初始化SharedPreference
     */
    private fun initPrefs() {
        SPFUtil.init(mInstance, packageName + "_preference", Context.MODE_MULTI_PROCESS)
    }

    companion object {
        private lateinit var mInstance:App
        fun instance(): App {
            return mInstance
        }
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }


}