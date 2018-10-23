package com.orz.kotlin_mvvm_demo.network.Func


import com.orz.kotlin_mvvm_demo.comment.event.TokenInvalidEvent
import com.orz.kotlin_mvvm_demo.network.HttpResult
import com.orz.kotlin_mvvm_demo.network.exception.NetworkException
import com.orz.kotlin_mvvm_demo.network.exception.ResponseException
import com.orz.kotlin_mvvm_demo.network.exception.TokenInvalidException
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Function
import org.greenrobot.eventbus.EventBus

class ParseResponseObservableFunction<T> : Function<HttpResult<T>, ObservableSource<HttpResult<T>>> {
    override fun apply(response: HttpResult<T>): ObservableSource<HttpResult<T>> {
        if (response == null) {
            return Observable.error(NetworkException("网络异常，请检查网络！"))
        }
        if (response.isTokenInvalid) {
            //EventBus.getDefault().post(TokenInvalidEvent())
            return Observable.error(TokenInvalidException("登录失效"))
        }
        return if (!response.isSuccess) {
            Observable.error(ResponseException(response))
        } else Observable.just(response)
    }
}

