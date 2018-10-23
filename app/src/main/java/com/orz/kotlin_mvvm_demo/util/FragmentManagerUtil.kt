package com.orz.kotlin_mvvm_demo.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction


/**
 * Create by orz on 2018/9/19
 * 多Fragment切换管理帮助类
 */
class FragmentManagerUtil(var contentViewId:Int, var fm:FragmentManager,var fClassList:List<Class<out Fragment>>){
    private var currentFragment: Fragment?=null
    private lateinit var transaction: FragmentTransaction


    fun switchFragment(position:Int){
        if (position>=fClassList.size){
            return
        }
        changeFragment(getTargetFragment(position),fClassList[position].simpleName)
    }

    private fun getTargetFragment(pos: Int): Fragment? {
        var fragment: Fragment? = fm.findFragmentByTag(fClassList[pos].simpleName)
        if (fragment == null) {
            fragment =  fClassList[pos].newInstance()
        }
        return fragment
    }


    private fun changeFragment(targetFragment: Fragment?, tag: String) {
        targetFragment?.let {
            transaction = fm.beginTransaction()
            if (currentFragment != null) {
                transaction.hide(currentFragment!!)
            }
            if (!it.isAdded) {
                transaction.add(contentViewId, it, tag)
            } else {
                transaction.show(it)
            }
            currentFragment = it
            transaction.commit()
        }
    }
}