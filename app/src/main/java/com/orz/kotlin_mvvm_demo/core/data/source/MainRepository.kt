package com.orz.kotlin_mvvm_demo.core.data.source

import com.orz.kotlin_mvvm_demo.callback.CallBack
import com.orz.kotlin_mvvm_demo.core.data.BaseRepository
import com.orz.kotlin_mvvm_demo.core.data.pojo.VersionInfo
import com.orz.kotlin_mvvm_demo.network.ApiService
import com.orz.kotlin_mvvm_demo.network.rx.io_main
import com.orz.kotlin_mvvm_demo.network.transformer.ParseTransformers.observableTransformer
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Create by Administrator on 2018/9/14
 */

@Singleton
class MainRepository @Inject constructor(private val apiService: ApiService) : BaseRepository() {
    fun asyncCheckAppUpdate( plat: Int, versionCode: Int,listener: CallBack<VersionInfo>) {
        val disposable = apiService.getVersionData(plat,versionCode)
                    .io_main()
                    .compose(observableTransformer())
                    .map { it.data() }
                    .subscribe({
                        it?.let {
                            listener.onNext(it)
                        }
                    }, {
                        listener.onError(it)
                    })

        disposable?.let { addSubscribe(it) }

    }
}


