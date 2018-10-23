package com.orz.kotlin_mvvm_demo.widget.tab.wrapper.impl
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import com.orz.kotlin_mvvm_demo.widget.tab.TabItem
import com.orz.kotlin_mvvm_demo.widget.tab.TabLayoutControllable
import com.orz.kotlin_mvvm_demo.widget.tab.wrapper.ITabLayoutWrapper

/**
 * [TabLayout]
 */
class TabLayoutWrapper(private val tabLayout: TabLayout,
                       private val controllable: TabLayoutControllable,
                       private val tabItems: List<TabItem<*, *>>) : ITabLayoutWrapper {

    override fun setupTab(manager: FragmentManager?, containerId: Int) {
        tabLayout.removeAllTabs()
    }

    override fun controllable(): TabLayoutControllable {
        return controllable
    }

    override fun tabItems(): List<TabItem<*, *>> {
        return tabItems
    }

    override fun addTab(tabItem: TabItem<*, *>) {
        tabLayout.addTab(tabLayout.newTab().setTag(tabItem).setText(tabItem.textId()))
    }

    override fun setCurrentTab(index: Int) {
        tabLayout.setScrollPosition(index, 0f, true)
        tabLayout.getTabAt(index)?.select()
    }

    override fun setTabText(index: Int, text: String) {
        tabLayout.getTabAt(index)?.text = text
    }

    override fun setOnItemSelectedListener(listener: ((tabItem: TabItem<*, *>) -> Unit)) {

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                listener.invoke((tab?.tag as TabItem<*, *>?)!!)
            }
        })
    }
}
