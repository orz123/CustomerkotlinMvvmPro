package com.orz.kotlin_mvvm_demo.widget.tab

import android.support.v4.app.Fragment
import com.orz.kotlin_mvvm_demo.widget.tab.wrapper.ITabLayoutWrapper


import java.util.HashMap

/**
 * [ITabLayoutWrapper]
 * [TabLayoutControllable]
 * [TabItem]
 *
 * --目标Fragment必须有无参构造方法
 * --0106-18 update 也可选择重写TabItem.build()
 */
class TabController @JvmOverloads
constructor(wrapper: ITabLayoutWrapper, switchMode: SwitchMode = SwitchMode.HIDE_SHOW) {

    private val wrapper: ITabLayoutWrapper = checkNotNull(wrapper, { "" })
    private val controllable: TabLayoutControllable = checkNotNull(wrapper.controllable(), { "" })
    private val tabItems: List<TabItem<*, *>> = checkNotNull(wrapper.tabItems(), { "" })
    private val switchMode: SwitchMode = checkNotNull(switchMode, { "" })

    private val fragmentMap = HashMap<TabItem<*, *>, Fragment>()
    private var currentFragment: Fragment? = null

    enum class SwitchMode {
        REPLACE,
        HIDE_SHOW
    }

    init {
        initTab()
    }

    private fun initTab() {
        val fragmentManager = controllable.fragmentManager()
        fragmentManager?.apply {
            if (!fragments.isEmpty()) {
                for (fragment in fragments) {
                    beginTransaction().remove(fragment).commit()
                }
                fragments.clear()
            }
        }
        wrapper.setupTab(fragmentManager, controllable.containerViewId())
        for (tabItem in tabItems) {
            wrapper.addTab(tabItem)
        }
        wrapper.setOnItemSelectedListener {
            switchContent(it)
            controllable.onTabItemSelected(it)
        }
    }

    private fun switchContent(tabItem: TabItem<*, *>) {
        when (switchMode) {
            SwitchMode.REPLACE -> switchContentByReplace(tabItem)
            SwitchMode.HIDE_SHOW -> switchContentByHideShow(tabItem)
        }
    }

    /**
     * switch Content by remove current Fragment and add target Fragment into fm.
     * hold Fragment instance if had created.
     */
    private fun switchContentByReplace(tabItem: TabItem<Fragment, *>) {
        val transaction = controllable.fragmentManager()?.beginTransaction()
        if (currentFragment != null && currentFragment!!.isAdded) {
            transaction?.remove(currentFragment!!)
        }
        var target: Fragment? = fragmentMap[tabItem]
        if (target == null) {
            target = tabItem.build()
            if (target == null) {
                return
            }
            fragmentMap[tabItem] = target
        }
        transaction?.add(controllable.containerViewId(), target)
        transaction?.show(target)?.commit()
        currentFragment = target
    }

    /**
     * switch Content by show target Fragment and hide others.
     * hold all Fragments instance.
     */
    private fun switchContentByHideShow(tabItem: TabItem<Fragment, *>) {
        val transaction = controllable.fragmentManager()?.beginTransaction()
        if (currentFragment != null && currentFragment!!.isAdded) {
            transaction?.hide(currentFragment!!)
        }
        var target: Fragment? = fragmentMap[tabItem]
        if (target == null) {
            target = tabItem.build()
            if (target == null) {
                return
            }
            fragmentMap[tabItem] = target
        }
        if (!target.isAdded) {
            transaction?.add(controllable.containerViewId(), target)
        }
        transaction?.show(target)?.commit()
        currentFragment = target
    }

    fun setCurrentTab(index: Int) {
        if (index < 0 || index >= tabItems.size) {
            return
        }
        wrapper.setCurrentTab(index)
        if (wrapper.switchContentAfterTabSelected || !fragmentMap.containsKey(tabItems[index])) {
            switchContent(tabItems[index])
        }
        controllable.onTabItemSelected(tabItems[index])
    }

    /**
     * setText, setTextSize, setTextColor...
     */
    fun setTabText(index: Int, text: String) {
        if (index < 0 || index >= tabItems.size) {
            return
        }
        wrapper.setTabText(index, text)
    }

    fun destroy() {
        val transaction = controllable.fragmentManager()?.beginTransaction()
        val fragments = controllable.fragmentManager()?.fragments
        if (fragments != null && fragments.size > 0) {
            for (fragment in fragments) {
                transaction?.remove(fragment)
            }
        }
        fragmentMap.clear()
        currentFragment = null
    }
}
