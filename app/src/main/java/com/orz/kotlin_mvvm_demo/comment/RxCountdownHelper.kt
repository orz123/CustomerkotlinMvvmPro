package com.orz.kotlin_mvvm_demo.comment

import android.widget.TextView
import com.orz.kotlin_mvvm_demo.callback.RxCountDownCallBack
import com.orz.kotlin_mvvm_demo.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


/**
 * @author: orz on 2018/9/27
 * 倒计时工具类
 */
object RxCountdownHelper {

    private var mObservable: Observable<Long>? = null
    private var mDisposable: Disposable? = null
    private var mConsumer: Consumer<Long>? = null
    private val MAX_COUNT_TIME: Long by lazy { 60L }


    fun startCountDown(mBtnSendMsm: TextView,listener: RxCountDownCallBack){
        mObservable = Observable.interval(1, TimeUnit.SECONDS)
                //续 1s.
                .take(MAX_COUNT_TIME + 1)
                .map { MAX_COUNT_TIME - it - 1 }
                .subscribeOn(Schedulers.computation())
                .doOnSubscribe {
                    mBtnSendMsm.isClickable = false
                    mBtnSendMsm.text = "剩余 $MAX_COUNT_TIME s"
                    listener.onSendMsg()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {
                    mBtnSendMsm.isClickable = true
                    mBtnSendMsm.setText(R.string.ui_regain_verification_code)
                }
        mConsumer = Consumer {
            mBtnSendMsm.text = "剩余 $it s"
        }
        mDisposable = mObservable?.subscribe(mConsumer)

    }

    fun cancelCountDown(){
        mDisposable?.dispose()
    }

}