package com.orz.kotlin_mvvm_demo.widget.tab.wrapper.impl

import android.support.v4.app.Fragment
import com.flyco.tablayout.listener.CustomTabEntity
import com.orz.kotlin_mvvm_demo.App
import com.orz.kotlin_mvvm_demo.widget.tab.TabItem

/**
 * TabItem for [com.flyco.tablayout.CommonTabLayout]
 * deps [CustomTabEntity]
 * */
open class CommonTabItem<E>(val index: Int, val textId: Int, val fragment: Fragment? = null, var data: E? = null) :
        TabItem<Fragment, Any>, CustomTabEntity {

    override fun getTabUnselectedIcon(): Int {
        return 0
    }

    override fun getTabSelectedIcon(): Int {
        return 0
    }

    override fun getTabTitle(): String {
        return App.instance().resources.getString(textId)
    }

    override fun index(): Int {
        return index
    }

    override fun textId(): Int {
        return textId
    }

    override fun build(): Fragment? {
        return fragment
    }

    override fun data(): E? {
        return data
    }
}