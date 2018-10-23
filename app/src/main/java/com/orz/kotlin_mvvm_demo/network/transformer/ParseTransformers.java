package com.orz.kotlin_mvvm_demo.network.transformer;


import com.orz.kotlin_mvvm_demo.network.HttpResult;
import com.orz.kotlin_mvvm_demo.network.Func.ParseExceptionCompletableFunction;
import com.orz.kotlin_mvvm_demo.network.Func.ParseExceptionObservableFunction;
import com.orz.kotlin_mvvm_demo.network.Func.ParseExceptionSingleFunction;
import com.orz.kotlin_mvvm_demo.network.Func.ParseResponseObservableFunction;
import com.orz.kotlin_mvvm_demo.network.Func.ParseResponseSingleFunction;

import io.reactivex.CompletableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;

/**
 * {@link HttpResult} Transformer
 * for:
 * {@link io.reactivex.Single}
 * {@link io.reactivex.Completable}
 * <p>
 * step1: parse response
 * - null -> return error;
 * - failure(code!=0) -> return error;
 * - success(code==0) -> return response;
 * step2: parse Exceptions to friendly messages
 * - ConnectException;
 * - SocketTimeoutException;
 * - ...
 */
public class ParseTransformers {

    private static final CompletableTransformer completableTransformer =
            upstream -> upstream.onErrorResumeNext(new ParseExceptionCompletableFunction());

    private static SingleTransformer singleTransformer =
            upstream -> upstream.flatMap(new ParseResponseSingleFunction<>())
                    .onErrorResumeNext(new ParseExceptionSingleFunction<>());

    private static ObservableTransformer observableTransformer =
            upstream -> upstream.flatMap(new ParseResponseObservableFunction<>())
                    .onErrorResumeNext(new ParseExceptionObservableFunction<>());

    //////////// utils
    public static <T> SingleTransformer<HttpResult<T>, HttpResult<T>> singleTransformer() {
        return (SingleTransformer<HttpResult<T>, HttpResult<T>>) singleTransformer;
    }

    public static <T> ObservableTransformer<HttpResult<T>, HttpResult<T>> observableTransformer() {
        return (ObservableTransformer<HttpResult<T>, HttpResult<T>>) observableTransformer;
    }
}