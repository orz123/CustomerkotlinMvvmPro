package com.orz.kotlin_mvvm_demo.core.view.splash

import android.content.Intent
import android.os.Handler
import com.orz.kotlin_mvvm_demo.R
import com.orz.kotlin_mvvm_demo.base.BaseActivity
import com.orz.kotlin_mvvm_demo.comment.PermissionHelper
import com.orz.kotlin_mvvm_demo.config.Constants
import com.orz.kotlin_mvvm_demo.core.view.guide.GuideActivity
import com.orz.kotlin_mvvm_demo.core.view.main.MainActivity
import com.orz.kotlin_mvvm_demo.util.SPFUtil


/**
 * @author: orz on 2018/9/20
 * 启动页Activity
 */
class SplashActivity :BaseActivity(){
    override val layoutId: Int by lazy { R.layout.activity_splash }

    override fun configViews() {

    }
    override fun initData() {
        if (0 !== intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) {
            finish()
            return
        }
        requestPermissions()
    }

    private fun requestPermissions() {
        PermissionHelper.requestPermissions(this,
                onComplete = {
                    val isFirstEnter = SPFUtil.instance?.getBoolean(Constants.FIRST_ENTRY,true)
                    isFirstEnter?.let { jump(it) }
                })
    }



    private fun jump(isFirstEnter:Boolean) {
        if (isFirstEnter) {
            Handler().postDelayed({ enterGuideActivity()  }, 2000)
        } else {
            Handler().postDelayed({ enterMainActivity()  }, 2000)
        }
    }

    private fun enterGuideActivity() {
        GuideActivity.jumpActivity(SplashActivity@this)
        finish()
    }

    private fun enterMainActivity() {
        MainActivity.jumpActivity(SplashActivity@this)
        finish()
    }
}