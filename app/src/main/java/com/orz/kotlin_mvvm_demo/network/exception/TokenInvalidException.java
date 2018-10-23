package com.orz.kotlin_mvvm_demo.network.exception;


public class TokenInvalidException extends RuntimeException {

    public TokenInvalidException(String message) {
        super(message);
    }
}
