package com.orz.kotlin_mvvm_demo.db.dao

import android.arch.persistence.room.*
import com.orz.kotlin_mvvm_demo.db.entity.RoleEntity

/**
 * @author: orz on 2018/9/20
 */
@Dao
abstract class RoleDao {

    @Query("SELECT * FROM tb_role ORDER BY name COLLATE NOCASE ASC")
    abstract fun findAll(): List<RoleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(role: RoleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(roles:List<RoleEntity>)

    @Delete
    abstract fun deleteAll(roles: List<RoleEntity>)
}