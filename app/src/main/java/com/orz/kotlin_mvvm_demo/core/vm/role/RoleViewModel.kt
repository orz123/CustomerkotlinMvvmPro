package com.orz.kotlin_mvvm_demo.core.vm.role

import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.orz.kotlin_mvvm_demo.di.Scopes
import com.orz.kotlin_mvvm_demo.core.view.paging.RoleFactory
import javax.inject.Inject

/**
 * @author: orz on 2018/9/21
 */
@Scopes.Activity
class RoleViewModel
@Inject
constructor(sourceFactory: RoleFactory):ViewModel() {

    val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .build()
    val allRoles = LivePagedListBuilder(sourceFactory,pagedListConfig).build()

}