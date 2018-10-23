package com.orz.kotlin_mvvm_demo.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.orz.kotlin_mvvm_demo.db.DbConstants

/**
 * @author: orz on 2018/9/20
 */
@Entity(tableName = DbConstants.TB_ROLE)
 class RoleEntity(
        @PrimaryKey(autoGenerate = true) var id: Int?=null,
        var name: String){

}
