package com.orz.kotlin_mvvm_demo.widget.tab.wrapper.impl


import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTabHost

import com.orz.kotlin_mvvm_demo.widget.tab.TabItem
import com.orz.kotlin_mvvm_demo.widget.tab.TabLayoutControllable
import com.orz.kotlin_mvvm_demo.widget.tab.wrapper.ITabLayoutWrapper

/**
 * [FragmentTabHost]
 */
class FragmentTabHostWrapper(private val tabHost: FragmentTabHost,
                             private val controllable: TabLayoutControllable,
                             private val tabItems: List<TabItem<*, *>>) : ITabLayoutWrapper {

    override fun controllable(): TabLayoutControllable {
        return controllable
    }

    override fun tabItems(): List<TabItem<*, *>> {
        return tabItems
    }

    override fun setupTab(manager: FragmentManager?, containerId: Int) {
        tabHost.setup(tabHost.context, manager, containerId)
        tabHost.tabWidget.dividerDrawable = null
        tabHost.clearAllTabs()
    }

    override fun addTab(tabItem: TabItem<*, *>) {
        val tabSpec = tabHost.newTabSpec(tabItem.tag())
                .setIndicator(controllable.buildTabItemView(tabItem))
                .setContent { s -> tabHost.tabContentView }
        tabHost.addTab(tabSpec)
    }

    override fun setCurrentTab(index: Int) {
        tabHost.currentTab = index
    }

    override fun setTabText(index: Int, text: String) {

    }

    override fun setOnItemSelectedListener(listener: ((tabItem: TabItem<*, *>) -> Unit)) {
        tabHost.setOnTabChangedListener { tabId -> listener.invoke(getTab(tabId)!!) }
    }

    private fun getTab(tabId: String): TabItem<*, *>? {
        for (item in tabItems) {
            if (item.tag() == tabId) {
                return item
            }
        }
        return null
    }
}
