package com.orz.kotlin_mvvm_demo.core.view.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.orz.kotlin_mvvm_demo.db.entity.RoleEntity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author: orz on 2018/9/21
 */
@Singleton
class RoleFactory
@Inject
constructor(var dataSource:RoleDataSource):DataSource.Factory<Int,RoleEntity>() {
    var sourceLiveData = MutableLiveData<RoleDataSource>()
    override fun create(): DataSource<Int, RoleEntity> {
        sourceLiveData.postValue(dataSource)
        return dataSource
    }
}
