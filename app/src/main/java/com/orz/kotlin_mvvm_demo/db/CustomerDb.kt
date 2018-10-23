package com.orz.kotlin_mvvm_demo.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.orz.kotlin_mvvm_demo.db.dao.RoleDao
import com.orz.kotlin_mvvm_demo.db.entity.RoleEntity

/**
 * @author: orz on 2018/9/20
 *  RoomDatabase数据库
 */
@Database(entities = [
    RoleEntity::class
], version = DbConstants.DB_VERSION, exportSchema = false)
abstract class CustomerDb:RoomDatabase(){
    abstract fun roleDao(): RoleDao
}