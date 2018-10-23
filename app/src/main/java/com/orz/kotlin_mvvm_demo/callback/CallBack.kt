package com.orz.kotlin_mvvm_demo.callback

/**
 * @author：orz on 18/7/31 12:33
 */
interface CallBack<T> {

    /**
     * no network
     */
    fun onNoNetWork(){}

    /**
     * @param t
     */
    fun onNext(t: T)

    /**
     * @param e
     */
    fun onError(e: Throwable)
}
