package com.orz.kotlin_mvvm_demo.core.view.paging

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.orz.kotlin_mvvm_demo.R
import com.orz.kotlin_mvvm_demo.R.id.recyclerView
import com.orz.kotlin_mvvm_demo.base.BaseActivity
import com.orz.kotlin_mvvm_demo.base.KeepTitleLoadSirActivity
import com.orz.kotlin_mvvm_demo.callback.loadsir.EmptyCallback
import com.orz.kotlin_mvvm_demo.callback.loadsir.ErrorCallback
import com.orz.kotlin_mvvm_demo.callback.loadsir.LoadingCallback
import com.orz.kotlin_mvvm_demo.core.vm.role.RoleViewModel
import com.orz.kotlin_mvvm_demo.db.entity.RoleEntity
import com.orz.kotlin_mvvm_demo.util.NetworkUtils
import kotlinx.android.synthetic.main.activity_role.*
import javax.inject.Inject

/**
 * @author: orz on 2018/9/21
 * android
 */
class RoleActivity:KeepTitleLoadSirActivity() {
    override val layoutId: Int by lazy { R.layout.activity_role }
    override val isInject: Boolean = true
    @Inject
    lateinit var roleViewModel: RoleViewModel
    lateinit var adapter:RoleAdapter
    override val contentResId: Int by lazy { R.id.recyclerView }

    override fun initToolBar() {
        setToolbarNavigationIcon(R.drawable.ic_back)
        setToolbarTitleText(R.string.ui_page_test)
    }
    override fun configViews() {
       adapter = RoleAdapter(R.layout.item_role)
        recyclerView.layoutManager = LinearLayoutManager(RoleActivity@this)
        recyclerView.adapter = adapter

    }

    override fun initData() {
        requestData()
    }

    private fun requestData() {
        roleViewModel.allRoles.observe(this, Observer {
            if (NetworkUtils.isNetworkAvailable(this)){
                bindData(it)
            }else{
                mLoadService?.showCallback(ErrorCallback::class.java)
            }
        })
    }

    private fun bindData(list: PagedList<RoleEntity>?) {
        if (list ==null&&adapter.itemCount==0){
            mLoadService?.showCallback(EmptyCallback::class.java)
        }
        mLoadService?.showSuccess()
        adapter.submitList(list)
    }

    override fun onNetReload(v: View?) {
        mLoadService?.showCallback(LoadingCallback::class.java)
        requestData()
    }

    companion object {
        fun jumpActivity(context: Context) {
            val intent = Intent(context, RoleActivity::class.java)
            context.startActivity(intent)
        }
    }
}