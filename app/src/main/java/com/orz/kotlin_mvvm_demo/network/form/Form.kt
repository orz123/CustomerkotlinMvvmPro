package com.orz.kotlin_mvvm_demo.network.form

import okhttp3.FormBody
import okhttp3.RequestBody

/**
 * Base Form with token or non.
 * */
open class Form : IForm<RequestBody>() {

    protected val builder: FormBody.Builder = FormBody.Builder()

    private var token: String? = null

    fun token(token: String): Form {
        this.token = token
        return this
    }

    fun add(key: String, value: Any?): Form {
        value?.let {
            builder.add(key, value.toString())
        }
        return this
    }

    override fun build(): RequestBody {
        add("session_token", token)
        return builder.build()
    }
}