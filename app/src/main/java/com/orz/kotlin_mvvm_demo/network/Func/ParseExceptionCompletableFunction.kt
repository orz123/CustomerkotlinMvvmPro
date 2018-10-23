package com.orz.kotlin_mvvm_demo.network.Func




import com.orz.kotlin_mvvm_demo.network.exception.NetworkException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.functions.Function
import retrofit2.HttpException


class ParseExceptionCompletableFunction : Function<Throwable, CompletableSource> {

    @Throws(Exception::class)
    override fun apply(throwable: Throwable): CompletableSource {
        if (throwable is ConnectException) {
            return Completable.error(NetworkException("网络连接错误"))
        }
        if (throwable is SocketException) {
            return Completable.error(NetworkException("网络连接错误"))
        }
        if (throwable is SocketTimeoutException) {
            return Completable.error(NetworkException("网络请求超时，请检查网络"))
        }
        if (throwable is UnknownHostException) {
            return Completable.error(NetworkException("网络异常，请检查网络"))
        }
        if (throwable is HttpException) {
            return Completable.error(NetworkException("网络请求失败，请稍后再试"))
        }

        return Completable.error(throwable)
    }
}
