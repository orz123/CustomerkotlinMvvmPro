package com.orz.kotlin_mvvm_demo.core.view.main.fragment

import android.widget.TextView
import com.orz.kotlin_mvvm_demo.di.Injectable
import com.orz.kotlin_mvvm_demo.R
import com.orz.kotlin_mvvm_demo.base.BaseFragment
import com.orz.kotlin_mvvm_demo.db.CustomerDb
import com.orz.kotlin_mvvm_demo.db.entity.RoleEntity
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.uiThread
import javax.inject.Inject

/**
 * Create by orz on 2018/9/19
 * 我的Fragment
 */
class MineFragment:BaseFragment(), Injectable {
    private lateinit var title: TextView

    override val layoutResId: Int by lazy { R.layout.fragment_mine }
    @Inject
    lateinit var customerDb: CustomerDb

    override fun configViews() {
        title = find(R.id.common_title)
        title.text = getString(R.string.ui_main_tab_mine)
    }

    override fun lazyLoad() {
     val db = customerDb.roleDao()
        async {
            db.insert(arrayListOf(RoleEntity(0,"红楼梦"),RoleEntity(1,"水浒传"),
                    RoleEntity(2,"西游记"),RoleEntity(3,"三国演义")))
            val list =  db.findAll()
            uiThread {
                find<TextView>(R.id.tv_content).text = list[0].name+"&&"+list[1].name+"&&"+list[2].name+"&&"+list[3].name
            }
        }


    }
}