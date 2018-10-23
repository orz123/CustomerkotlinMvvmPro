package com.orz.kotlin_mvvm_demo.widget.tab


import android.support.v4.app.Fragment

interface TabItem<out T : Fragment, out E : Any> {

    fun fragmentClass(): Class<out T>? {
        return null
    }

    fun build(): T? {
        try {
            return fragmentClass()!!.newInstance()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    fun index(): Int {
        return 0
    }

    fun textId(): Int

    fun iconId(): Int {
        return 0
    }

    fun tag(): String {
        return toString()
    }

    fun data(): E? {
        return null
    }
}
