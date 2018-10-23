package com.orz.kotlin_mvvm_demo.widget.tab.wrapper


import android.support.v4.app.FragmentManager

import com.orz.kotlin_mvvm_demo.widget.tab.TabItem
import com.orz.kotlin_mvvm_demo.widget.tab.TabLayoutControllable

interface ITabLayoutWrapper {

    fun controllable(): TabLayoutControllable

    fun tabItems(): List<TabItem<*, *>>

    fun setupTab(manager: FragmentManager?, containerId: Int)

    fun addTab(tabItem: TabItem<*, *>)

    fun setCurrentTab(index: Int)

    fun setTabText(index: Int, text: String)

    val switchContentAfterTabSelected: Boolean
        get() = false

    fun setOnItemSelectedListener(listener: ((tabItem: TabItem<*, *>) -> Unit))
}
