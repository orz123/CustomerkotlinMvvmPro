package com.orz.kotlin_mvvm_demo.network.rx


import android.content.Context

abstract class RxSubscriber<T>(context: Context) : BaseSubscriber<T>(context) {
    override fun _onStart() {
        showLoading()
    }

    override fun _onNext(t: T) {
        onSuccess(t)
    }

    override fun _onError(e: Throwable) {
        e.message?.let { onFailure(it) }
    }

    override fun _onComplete() {

    }


    protected fun showLoading() {

    }


    /**
     * success
     *
     * @param t
     */
    abstract fun onSuccess(t: T)

    /**
     * failure
     *
     * @param msg
     */
    abstract fun onFailure(msg: String)

}
