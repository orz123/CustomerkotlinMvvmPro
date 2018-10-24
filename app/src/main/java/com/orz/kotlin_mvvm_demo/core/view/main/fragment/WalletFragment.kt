package com.orz.kotlin_mvvm_demo.core.view.main.fragment

import android.os.Handler
import android.widget.TextView
import com.orz.kotlin_mvvm_demo.R
import com.orz.kotlin_mvvm_demo.base.BaseFragment
import com.orz.kotlin_mvvm_demo.comment.ProgressDialogHelper
import com.orz.kotlin_mvvm_demo.di.Injectable
import org.jetbrains.anko.support.v4.find

/**
 * Create by orz on 2018/9/19
 * 钱包Fragment
 */
class WalletFragment:BaseFragment(), Injectable {
    private lateinit var title: TextView

    override val layoutResId: Int by lazy { R.layout.fragment_wallet }

    override fun configViews() {
        title = find(R.id.common_title)
        title.text = getString(R.string.ui_main_tab_wallet)
    }



    override fun lazyLoad() {

    }

    override fun onVisible(){
        super.onVisible()
        activity?.run { ProgressDialogHelper.init(this).showDialog() }
        Handler().postDelayed({
            ProgressDialogHelper.dismissDialog()
        },2000)
    }

}