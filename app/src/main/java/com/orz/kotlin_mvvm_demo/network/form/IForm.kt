package com.orz.kotlin_mvvm_demo.network.form

import okhttp3.RequestBody

/**
 * Base Form object.
 * will be converted to [RequestBody] when request
 */
abstract class IForm<out T : RequestBody> {

    abstract fun build(): T
}
