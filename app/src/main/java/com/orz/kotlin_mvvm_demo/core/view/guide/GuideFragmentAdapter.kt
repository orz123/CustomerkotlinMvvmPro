package com.orz.kotlin_mvvm_demo.core.view.guide

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class GuideFragmentAdapter(fm: FragmentManager):FragmentPagerAdapter(fm) {
    private var mFragments: List<Fragment>? = null

    fun setFragments(fragments:List<Fragment>){
        mFragments = fragments
    }
    override fun getItem(position: Int): Fragment {
        return mFragments?.get(position)!!
    }

    override fun getCount(): Int {
        return mFragments?.size?:0
    }
}