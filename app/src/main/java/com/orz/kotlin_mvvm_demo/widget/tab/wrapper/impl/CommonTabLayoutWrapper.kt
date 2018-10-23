package com.orz.kotlin_mvvm_demo.widget.tab.wrapper.impl

import android.support.v4.app.FragmentManager
import com.flyco.tablayout.CommonTabLayout
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.orz.kotlin_mvvm_demo.widget.tab.TabItem
import com.orz.kotlin_mvvm_demo.widget.tab.TabLayoutControllable
import com.orz.kotlin_mvvm_demo.widget.tab.wrapper.ITabLayoutWrapper
import java.util.ArrayList

/**
 * [ITabLayoutWrapper]
 * [CommonTabLayout]
 * */
class CommonTabLayoutWrapper(private val tabLayout: CommonTabLayout,
                             private val controllable: TabLayoutControllable,
                             private val tabItems: List<CommonTabItem<Any>>,
                             private val listener: OnTabSelectListener? = null) : ITabLayoutWrapper {
    override fun controllable(): TabLayoutControllable {
        return controllable
    }

    override fun tabItems(): List<CommonTabItem<Any>> {
        return tabItems
    }

    override fun setupTab(manager: FragmentManager?, containerId: Int) {
        tabLayout.setTabData(tabItems as ArrayList<CustomTabEntity>)
    }

    override fun addTab(tabItem: TabItem<*, *>) {

    }

    override fun setCurrentTab(index: Int) {
        tabLayout.currentTab = index
    }

    override fun setTabText(index: Int, text: String) {

    }

    override val switchContentAfterTabSelected: Boolean
        get() = true

    override fun setOnItemSelectedListener(listener: ((tabItem: TabItem<*, *>) -> Unit)) {
        tabLayout.setOnTabSelectListener(TabSelectedListener(listener))
    }

    private inner class TabSelectedListener(private val listener: ((tabItem: TabItem<*, *>) -> Unit)) : OnTabSelectListener {

        override fun onTabSelect(position: Int) {
            listener.invoke(tabItems[position])
        }

        override fun onTabReselect(position: Int) {

        }
    }
}