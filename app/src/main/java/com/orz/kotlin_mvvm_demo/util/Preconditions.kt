package com.orz.kotlin_mvvm_demo.util

/**
 * @author：orz on 18/9/9 11:30
 */
object Preconditions {
    fun <T> checkNotNull(reference: T?): T {
        if (reference == null) {
            throw NullPointerException()
        }
        return reference
    }
}
