package com.orz.kotlin_mvvm_demo.network.exception;


import android.text.TextUtils;

import com.orz.kotlin_mvvm_demo.network.HttpResult;

public class ResponseException extends RuntimeException {

    public final HttpResult response;

    public ResponseException(HttpResult response) {
        this.response = response;
    }

    @Override
    public String getMessage() {
        return response != null && !TextUtils.isEmpty(response.message()) ?
                response.message()
                : super.getMessage();
    }
}