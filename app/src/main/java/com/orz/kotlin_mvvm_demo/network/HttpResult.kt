package com.orz.kotlin_mvvm_demo.network

import com.google.gson.annotations.SerializedName


/*
* Base HttpResult
* -not needed if REST
* */
class HttpResult<out T> {

    @SerializedName("rt_code")
    private var code: Int = 0
    private var data: T? = null
    @SerializedName("msg")
    private var message: String? = null

    val isSuccess: Boolean
        get() = CODE_SUCCESS == code

    val isTokenInvalid: Boolean
        get() = CODE_INVALID_TOKEN == code

    val isResetInitialPassword: Boolean
        get() = CODE_RESET_INITIAL_PASSWORD == code

    fun code(): Int = code

    fun data(): T? = data

    fun message(): String? = message

    companion object {
        /**
         * 注意：rt_code = 128, msg = '请重置初始密码'时，
         * 是用户通过第三方登录创建账号后，
         * 又使用绑定的手机号进行登录产生的错误提示。
         * 出现此提示时，跳转至“设置密码”页面（此页面与找回密码页面内容完全相同）
         */
        const val CODE_RESET_INITIAL_PASSWORD = 128
        const val CODE_INVALID_TOKEN = 2
        const val CODE_SUCCESS = 1
    }
}
