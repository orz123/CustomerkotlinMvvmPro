package com.orz.kotlin_mvvm_demo.core.view.main.fragment

import android.widget.Button
import android.widget.TextView
import com.orz.kotlin_mvvm_demo.di.Injectable
import com.orz.kotlin_mvvm_demo.R
import com.orz.kotlin_mvvm_demo.base.BaseFragment
import com.orz.kotlin_mvvm_demo.core.view.paging.RoleActivity
import org.jetbrains.anko.support.v4.find

/**
 * Create by orz on 2018/9/19
 * 合约Fragment
 */
class ContractFragment:BaseFragment(), Injectable {
    private lateinit var title: TextView

    override val layoutResId: Int by lazy { R.layout.fragment_contract }

    override fun configViews() {
        title = find(R.id.common_title)
        title.text = getString(R.string.ui_main_tab_contract)
        find<Button>(R.id.btn_paging).setOnClickListener {
            RoleActivity.jumpActivity(this.activity!!)
        }
    }

    override fun lazyLoad() {

    }
}