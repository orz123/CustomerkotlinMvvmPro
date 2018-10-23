package com.orz.kotlin_mvvm_demo.network

import com.orz.kotlin_mvvm_demo.BuildConfig
import com.orz.kotlin_mvvm_demo.util.Preconditions

import java.util.concurrent.TimeUnit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author：orz on 18/4/18 17:19
 */
class HttpHelper private constructor() {


    class Builder {
        internal var mOkHttpClient: OkHttpClient? = null

        internal var mBuilder: OkHttpClient.Builder? = null

        internal var mRetrofit: Retrofit? = null

        /**
         * create OkHttp 初始化OKHttpClient,设置缓存,设置超时时间,设置打印日志
         *
         * @return Builder
         */
        fun initOkHttp(): Builder {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            if (mBuilder == null) {
                synchronized(HttpHelper::class.java) {
                    if (mBuilder == null) {
                        mBuilder = OkHttpClient.Builder()
                                .sslSocketFactory(SSLConfig.sslSocketFactoryDebug(), SSLConfig.trustManagerDebug())
                                .connectTimeout(30, TimeUnit.SECONDS)
                                .writeTimeout(30, TimeUnit.SECONDS)
                                .readTimeout(30, TimeUnit.SECONDS)
                        if (BuildConfig.DEBUG) {
                            mBuilder?.addInterceptor(interceptor)
                        }
                    }
                }
            }

            return this
        }

        /**
         * 添加拦截器
         *
         * @param mInterceptor Interceptor
         * @return Builder
         */
        fun addInterceptor(mInterceptor: Interceptor): Builder {
            checkNotNull(mInterceptor)
            this.mBuilder!!.addNetworkInterceptor(mInterceptor)
            return this
        }

        /**
         * create retrofit
         *
         * @param baseUrl baseUrl
         * @return Builder
         */
        fun createRetrofit(baseUrl: String): Builder {
            Preconditions.checkNotNull(baseUrl)
            val builder = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
            BASE_URL = baseUrl
            this.mOkHttpClient = mBuilder?.build()
            this.mRetrofit = builder.client(mOkHttpClient!!)
                    .build()
            return this
        }

        fun build() {
            HttpHelper.instance?.build(this)
        }

    }

    private fun build(builder: Builder) {
        checkNotNull(builder)
        checkNotNull(builder.mBuilder)
        checkNotNull(builder.mOkHttpClient)
        checkNotNull(builder.mRetrofit)
        mBuilder = builder.mBuilder
        mOkHttpClient = builder.mOkHttpClient
        mRetrofit = builder.mRetrofit
    }

    fun <T> create(clz: Class<T>): T {
        Preconditions.checkNotNull(clz)
        Preconditions.checkNotNull(mRetrofit)
        return mRetrofit!!.create(clz)
    }

    companion object {

        @Volatile
        private var mHttpHelper: HttpHelper? = null

        private var mOkHttpClient: OkHttpClient? = null

        private var mRetrofit: Retrofit? = null

        private var mBuilder: OkHttpClient.Builder? = null

        private var BASE_URL: String? = null

        val instance: HttpHelper?
            get() {
                if (mHttpHelper == null) {
                    synchronized(HttpHelper::class.java) {
                        if (mHttpHelper == null) {
                            mHttpHelper = HttpHelper()
                        }
                    }
                }
                return mHttpHelper
            }

        fun init(baseUrl: String) {
            Builder()
                    .initOkHttp()
                    .createRetrofit(baseUrl)
                    .build()
        }
    }

}
