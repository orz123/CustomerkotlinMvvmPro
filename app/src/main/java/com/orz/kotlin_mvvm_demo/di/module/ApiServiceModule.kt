package com.orz.kotlin_mvvm_demo.di.module

import com.orz.kotlin_mvvm_demo.network.ApiService
import com.orz.kotlin_mvvm_demo.network.HttpHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author: orz on 2018/10/20
 */
@Module
class ApiServiceModule {

    @Singleton
    @Provides
    internal fun provideApiService(): ApiService {
        return HttpHelper.instance?.create(ApiService::class.java)!!
    }
}
