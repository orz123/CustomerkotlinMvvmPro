package com.orz.kotlin_mvvm_demo.network.Func

import com.orz.kotlin_mvvm_demo.comment.event.TokenInvalidEvent
import com.orz.kotlin_mvvm_demo.network.HttpResult
import com.orz.kotlin_mvvm_demo.network.exception.NetworkException
import com.orz.kotlin_mvvm_demo.network.exception.ResponseException
import com.orz.kotlin_mvvm_demo.network.exception.TokenInvalidException
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.functions.Function
import org.greenrobot.eventbus.EventBus

class ParseResponseSingleFunction<T> : Function<HttpResult<T>, SingleSource<HttpResult<T>>> {
    override fun apply(response: HttpResult<T>): SingleSource<HttpResult<T>> {
        if (response == null) {
            return Single.error(NetworkException("网络异常，请检查网络！"))
        }
        if (response.isTokenInvalid) {
          //  EventBus.getDefault().post(TokenInvalidEvent())
            return Single.error(TokenInvalidException("登录失效"))
        }
        return if (!response.isSuccess) {
            Single.error(ResponseException(response))
        } else Single.just(response)
    }
}
