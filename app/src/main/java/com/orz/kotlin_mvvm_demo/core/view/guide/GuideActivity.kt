package com.orz.kotlin_mvvm_demo.core.view.guide

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.View
import com.orz.kotlin_mvvm_demo.R
import com.orz.kotlin_mvvm_demo.base.BaseActivity
import com.orz.kotlin_mvvm_demo.config.Constants
import com.orz.kotlin_mvvm_demo.core.view.main.MainActivity
import com.orz.kotlin_mvvm_demo.core.vm.guide.GuideViewModel
import com.orz.kotlin_mvvm_demo.util.SPFUtil
import kotlinx.android.synthetic.main.activity_guide.*
import javax.inject.Inject

/**
 * @author: orz on 2018/9/20
 * 引导页Activity
 */
class GuideActivity :BaseActivity(), ViewPager.OnPageChangeListener{
    override val layoutId: Int by lazy { R.layout.activity_guide }
    private lateinit var mAdapter: GuideFragmentAdapter
    override val isInject: Boolean = true
    @Inject
    lateinit var guideViewModel: GuideViewModel

    override fun configViews() {

    }

    override fun initData() {
        val fragments = mutableListOf<Fragment>()
        fragments.add(GuideFragment.newInstance(R.drawable.ic_guide_1))
        fragments.add(GuideFragment.newInstance(R.drawable.ic_guide_2))
        fragments.add(GuideFragment.newInstance(R.drawable.ic_guide_3))
        mAdapter = GuideFragmentAdapter(supportFragmentManager)
        mAdapter.setFragments(fragments)
        vpGuide.currentItem = 0
        vpGuide.offscreenPageLimit = mAdapter.count
        vpGuide.addOnPageChangeListener(this)
        vpGuide.adapter = mAdapter
    }

    override fun onPageScrollStateChanged(state: Int){}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {
        if(position == mAdapter.count-1){
            mBtnEnter.visibility = View.VISIBLE
            mBtnEnter.setOnClickListener {
                SPFUtil.instance?.putBoolean(Constants.FIRST_ENTRY,false)
                MainActivity.jumpActivity(GuideActivity@this)
                finish()
            }
        } else {
            mBtnEnter.visibility = View.GONE
        }

    }

    companion object {
        fun jumpActivity(context: Context) {
            val intent = Intent(context, GuideActivity::class.java)
            ContextCompat.startActivity(context,intent,null)
        }
    }

}