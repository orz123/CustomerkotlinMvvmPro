package com.orz.kotlin_mvvm_demo.core.view.paging

import android.arch.paging.ItemKeyedDataSource
import com.orz.kotlin_mvvm_demo.db.CustomerDb
import com.orz.kotlin_mvvm_demo.db.entity.RoleEntity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author: orz on 2018/9/21
 * DataSource
 */
@Singleton
class RoleDataSource
@Inject
constructor(private val customerDb: CustomerDb) : ItemKeyedDataSource<Int, RoleEntity>() {
    var page: Int = 1
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<RoleEntity>) {
        //初始请求数据，必须要同步请求
        callback.onResult(arrayListOf(RoleEntity(0, "orz0"), RoleEntity(1, "orz1"), RoleEntity(2, "orz2"), RoleEntity(3, "orz3")
                , RoleEntity(4, "orz4"), RoleEntity(5, "orz5"), RoleEntity(6, "orz6"), RoleEntity(7, "orz7"), RoleEntity(8, "orz8")
                , RoleEntity(9, "orz9"), RoleEntity(10, "orz10"), RoleEntity(11, "orz11")))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<RoleEntity>) {
        //请求后续数据异步
        page++
        if (page<6){
            callback.onResult(arrayListOf(RoleEntity(page+0, "orz0$page"), RoleEntity(page+1, "orz1$page"), RoleEntity(page+2, "orz2$page"), RoleEntity(page+3, "orz3$page")
                    , RoleEntity(page+4, "orz4$page"), RoleEntity(page+5, "orz5$page"), RoleEntity(page+6, "orz6$page"), RoleEntity(page+7, "orz7$page"), RoleEntity(page+8, "orz8$page")
                    , RoleEntity(page+9, "orz9$page"), RoleEntity(page+10, "orz10$page"), RoleEntity(page+11, "orz11$page")))
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<RoleEntity>) {

    }

    override fun getKey(item: RoleEntity): Int {
        return page
    }
}