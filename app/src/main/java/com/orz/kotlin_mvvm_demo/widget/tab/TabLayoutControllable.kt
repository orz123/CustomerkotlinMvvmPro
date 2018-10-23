package com.orz.kotlin_mvvm_demo.widget.tab


import android.support.v4.app.FragmentManager
import android.view.View
import com.orz.kotlin_mvvm_demo.widget.tab.TabItem

interface TabLayoutControllable {

    fun containerViewId(): Int {
        return 0
    }

    fun fragmentManager(): FragmentManager? {
        return null
    }

    fun buildTabItemView(tabItem: TabItem<*, *>): View? {
        return null
    }

    fun onTabItemSelected(tabItem: TabItem<*, *>) {

    }
}
