package com.orz.kotlin_mvvm_demo.network.Func


import com.orz.kotlin_mvvm_demo.network.exception.NetworkException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Function
import retrofit2.HttpException

class ParseExceptionObservableFunction<T> : Function<Throwable, ObservableSource<T>> {
    @Throws(Exception::class)
    override fun apply(throwable: Throwable): ObservableSource<T> {
        if (throwable is ConnectException) {
            return Observable.error(NetworkException("网络连接错误！"))
        }
        if (throwable is SocketException) {
            return Observable.error(NetworkException("网络连接错误！"))
        }
        if (throwable is SocketTimeoutException) {
            return Observable.error(NetworkException("网络请求超时，请检查网络！"))
        }
        if (throwable is UnknownHostException) {
            return Observable.error(NetworkException("网络异常，请检查网络！"))
        }
        return if (throwable is HttpException) {
            Observable.error(NetworkException("网络请求失败，请稍后再试！"))
        } else Observable.error(throwable)

    }
}
