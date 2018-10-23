package com.orz.kotlin_mvvm_demo.core.data


import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * @author：orz on 18/7/26 16:15
 */
abstract class BaseRepository {

    private var mCompositeDisposable: CompositeDisposable? = null

    protected fun addSubscribe(disposable: Disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable?.add(disposable)
    }

    fun unSubscribe() {
        mCompositeDisposable?.let {
            if(!it.isDisposed){
                it.isDisposed
            }
        }
    }
}
