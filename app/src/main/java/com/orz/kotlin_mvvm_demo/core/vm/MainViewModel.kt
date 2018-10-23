package com.orz.kotlin_mvvm_demo.core.vm

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.orz.kotlin_mvvm_demo.App
import com.orz.kotlin_mvvm_demo.di.Scopes
import com.orz.kotlin_mvvm_demo.comment.vm.Resource
import com.orz.kotlin_mvvm_demo.callback.CallBack
import com.orz.kotlin_mvvm_demo.config.Constants
import com.orz.kotlin_mvvm_demo.core.data.pojo.VersionInfo
import com.orz.kotlin_mvvm_demo.core.data.pojo.VersionVo
import com.orz.kotlin_mvvm_demo.core.data.source.MainRepository
import com.orz.kotlin_mvvm_demo.util.AppUtils
import javax.inject.Inject


/**
 * Create by Administrator on 2018/9/15
 */
@Scopes.Activity
class MainViewModel
@Inject
constructor(private val repository: MainRepository) : ViewModel() {

    fun requestAppUpdate(versionCode: Int): MutableLiveData<Resource<VersionInfo>> {
        var result = MutableLiveData<Resource<VersionInfo>>()
        repository.asyncCheckAppUpdate(Constants.PLAT_TYPE, versionCode,object : CallBack<VersionInfo> {
            override fun onNext(t: VersionInfo) {
                result.postValue(Resource.success(t))

            }

            override fun onError(e: Throwable) {
                result.postValue(Resource.error(e))
            }

        })

        return result

    }

    override fun onCleared() {
        super.onCleared()
        repository.unSubscribe()
    }
}