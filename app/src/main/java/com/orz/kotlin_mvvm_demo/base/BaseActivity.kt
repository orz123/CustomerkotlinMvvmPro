package com.orz.kotlin_mvvm_demo.base

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.orz.kotlin_mvvm_demo.R
import com.orz.kotlin_mvvm_demo.config.StatusBarCompat
import dagger.android.AndroidInjection
import org.greenrobot.eventbus.EventBus


/**
 * @author：orz on 18/9/12 19:22
 */

abstract class BaseActivity : AppCompatActivity() {

    protected var rootView: View? = null
    private var statusBarColor = 0
    private var statusBarView: View? = null
    private var mCommonToolbar: Toolbar? = null
    private var mTitle: TextView? = null
    private var mRightTv: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        if (isInject) {
            AndroidInjection.inject(this)
        }
        super.onCreate(savedInstanceState)
        rootView = View.inflate(this, layoutId, null)

        //设置布局内容
        setContentView(rootView)
        //状态栏
        initStatusBar()
        mCommonToolbar = findViewById(R.id.common_toolbar)
        mTitle = findViewById(R.id.common_title)
        mRightTv = findViewById(R.id.tv_common_right)
        if (mCommonToolbar != null) {
            initToolBar()
            mCommonToolbar?.setNavigationOnClickListener {
                onBackPressed()
            }
        }
        configLoadSir()
        configViews()
        registerEvent()
        initData()

    }

    protected open fun configLoadSir(){}


    protected open val isHasBus: Boolean
        get() = false

    private fun registerEvent() {
        if (isHasBus){
            EventBus.getDefault().register(this)
        }
    }

    protected open val isInject: Boolean
        get() = false

    /**
     * 设置布局layout
     * @return
     */
    abstract val layoutId: Int


    /**
     * 初始化toolbar
     */
    protected open fun initToolBar() {}

    /**
     * 对各种控件进行设置、适配
     */
    abstract fun configViews()

    /**
     * 数据初始化
     */
    abstract fun initData()



    private fun initStatusBar() {
        if (statusBarColor == 0) {
            statusBarView = StatusBarCompat.compat(this, ContextCompat.getColor(this, R.color.colorPrimaryDark))
        } else if (statusBarColor != -1) {
            statusBarView = StatusBarCompat.compat(this, statusBarColor)
        }
        transparent19and20()
    }



    protected fun setToolbarBackground(@DrawableRes resId: Int) {
        mCommonToolbar?.let {
            it.setBackgroundResource(resId)
        }
    }


    protected fun setToolbarNavigationIcon(resId: Int) {
        mCommonToolbar?.let {
            it.setNavigationIcon(resId)
        }
    }

    protected fun setToolbarTitleText(title: Int) {
        setToolbarTitleText(getString(title))

    }

    protected fun setToolbarTitleText(title: String) {
        mCommonToolbar?.let {
            mTitle?.setText(title)
        }
    }

    protected fun setToolbarRightText(rightStr: Int) {
        mCommonToolbar?.let {
            mRightTv?.let {it1->
                it1.visibility = View.VISIBLE
                it1.text = getString(rightStr)
                it1.setOnClickListener {
                    onToolbarRightClicked()
                }
            }

        }
    }

    protected open fun onToolbarRightClicked(){

    }


    private fun transparent19and20() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun toolbarSetElevation(elevation: Float) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mCommonToolbar?.elevation = elevation
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        if (isHasBus){
            EventBus.getDefault().unregister(this)
        }
    }

}

