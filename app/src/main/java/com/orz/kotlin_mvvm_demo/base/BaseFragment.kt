package com.orz.kotlin_mvvm_demo.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.greenrobot.eventbus.EventBus

/**
 * @author：orz on 18/9/12 19:22
 * 懒加载Fragment
 */
abstract class BaseFragment : Fragment() {
    protected var rootView: View? = null

    internal open var activity: FragmentActivity? = null


    protected var mIsFirstVisible = true

    /**
     * @return
     */
    abstract val layoutResId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        rootView = inflater.inflate(layoutResId, null, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isVis = isHidden || userVisibleHint
        configViews()
        registerEvent()
        if (isVis && mIsFirstVisible) {
            lazyLoad()
            mIsFirstVisible = false
        }


    }

    /**
     * 初始化views
     */
    abstract fun configViews()


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            onVisible()
        } else {
            onInVisible()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            onVisible()
        } else {
            onInVisible()
        }
    }

    /**
     * 当界面可见时的操作
     */
    protected fun onVisible() {
        if (mIsFirstVisible && isResumed) {
            lazyLoad()
            mIsFirstVisible = false
        }
    }

    protected open val isHasBus: Boolean
        get() = false

    private fun registerEvent() {
        if (isHasBus){
            EventBus.getDefault().register(this)
        }
    }

    /**
     * 数据懒加载
     */
    abstract fun lazyLoad()

    /**
     * 当界面不可见时的操作
     */
    protected fun onInVisible() {

    }

    protected fun <T : View> getViewById(id: Int): T {
        return rootView!!.findViewById<View>(id) as T
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.activity = context as FragmentActivity?
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.activity = null
    }

    override fun onDetach() {
        super.onDetach()
        if (isHasBus){
            EventBus.getDefault().unregister(this)
        }
        this.activity = null
    }


}