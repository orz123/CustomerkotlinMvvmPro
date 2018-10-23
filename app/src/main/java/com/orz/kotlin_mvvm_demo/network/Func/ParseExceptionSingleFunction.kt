package com.orz.kotlin_mvvm_demo.network.Func

import com.orz.kotlin_mvvm_demo.network.exception.NetworkException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.functions.Function
import retrofit2.HttpException

class ParseExceptionSingleFunction<T> : Function<Throwable, SingleSource<T>> {
    @Throws(Exception::class)
    override fun apply(throwable: Throwable): SingleSource<T> {
        if (throwable is ConnectException) {
            return Single.error(NetworkException("网络连接错误！"))
        }
        if (throwable is SocketException) {
            return Single.error(NetworkException("网络连接错误！"))
        }
        if (throwable is SocketTimeoutException) {
            return Single.error(NetworkException("网络请求超时，请检查网络！"))
        }
        if (throwable is UnknownHostException) {
            return Single.error(NetworkException("网络网络异常，请检查网络！"))
        }
        if (throwable is HttpException) {
            return Single.error(NetworkException("网络请求失败，请稍后再试！"))
        }

        return Single.error<T>(throwable)
    }
}