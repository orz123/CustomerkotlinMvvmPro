package com.orz.kotlin_mvvm_demo.comment.vm

import android.arch.lifecycle.Observer

open class ResourceObserver<T>(private val onSuccess: ((data: T?) -> Unit)? = null,
                               private val onError: ((message: String?) -> Unit)? = null,
                               private val onLoading: ((Any) -> Unit)? = null,
                               private val onCancel: ((message: String?) -> Unit)? = null) : Observer<Resource<T>> {

    override fun onChanged(resource: Resource<T>?) {
        when (resource?.status) {
            Status.LOADING -> onLoading?.invoke(Unit)
            Status.SUCCESS -> onSuccess?.invoke(resource.data)
            Status.ERROR -> onError?.invoke(resource.message ?: resource.error?.message)
            Status.CANCEL -> onCancel?.invoke(resource.message ?: resource.error?.message)
            else -> {
            }
        }
    }
}