/*
 * Copyright (C) 2017 zhouyou(478319399@qq.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.orz.kotlin_mvvm_demo.network.rx

import android.content.Context
import android.widget.Toast
import com.orz.kotlin_mvvm_demo.util.NetworkUtils
import io.reactivex.annotations.NonNull
import io.reactivex.observers.DisposableObserver
import java.lang.ref.WeakReference


/**
 *
 * 描述：订阅的基类
 * 1.可以防止内存泄露。<br></br>
 * 2.在onStart()没有网络时直接onCompleted();<br></br>
 * 3.统一处理了异常<br></br>
 * 作者： orz<br></br>
 * 日期： 2018/8/20 10:35<br></br>
 * 版本： v2.0<br></br>
 */
abstract class BaseSubscriber<T>(context: Context) : DisposableObserver<T>() {
    var contextWeakReference: WeakReference<Context>? = null

    override fun onStart() {
        contextWeakReference?.let {
            if (it.get() != null && !NetworkUtils.isNetworkAvailable(it.get()!!)) {
                Toast.makeText(it.get(), "暂无网络，请检查网络", Toast.LENGTH_SHORT).show()
                onComplete()
            }
        }
        _onStart()
    }


    init {
        contextWeakReference = WeakReference(context)
    }

    override fun onNext(@NonNull t: T) {
        _onNext(t)
    }

    override fun onError(e: Throwable) {
        _onError(e)
    }

    override fun onComplete() {
        _onComplete()
    }

    abstract fun _onStart()

    abstract fun _onNext(@NonNull t: T)

    abstract fun _onError(e: Throwable)

    abstract fun _onComplete()


}