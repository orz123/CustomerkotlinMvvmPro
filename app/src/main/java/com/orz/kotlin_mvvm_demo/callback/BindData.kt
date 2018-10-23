package com.orz.kotlin_mvvm_demo.callback

/**
 * @author: orz on 2018/9/21
 * Adapter数据绑定工具
 */
interface BindData<T> {
    fun bindTo(t: T?)
}