package com.orz.kotlin_mvvm_demo.config

import com.orz.kotlin_mvvm_demo.core.data.pojo.UserEntity
import com.orz.kotlin_mvvm_demo.util.SPFUtil

/**
 * @author: orz on 2018/10/12
 * 用户信息管理类
 */
object UserControl {
    private var token:String?=null
    private var user:UserEntity?=null

    fun setToken(token:String){
        UserControl.token = token
        saveToke(token)
    }

    fun getToken():String{
        if (token.isNullOrEmpty()){
            token = SPFUtil.instance?.getString(Constants.SESSION_TOKEN)
        }
        return token.toString()
    }

    private fun saveToke(token: String) {
        SPFUtil.instance?.putString(Constants.SESSION_TOKEN,token)
    }

    fun setCurrentUser(userEntity: UserEntity){
        user = userEntity
        saveUserSp(userEntity)
    }

    fun getCurrentUser(): UserEntity? {
        if (user == null){
            user = SPFUtil.instance?.getObject(Constants.USER_INFO,UserEntity::class.java)
        }
        return user
    }

    private fun saveUserSp(userEntity: UserEntity) {
        SPFUtil.instance?.putObject(Constants.USER_INFO,UserEntity::class.java)
    }

    fun isLogin():Boolean{
        val user = SPFUtil.instance?.getObject(Constants.USER_INFO,UserEntity::class.java)
        if (user != null){
            return true
        }
        return false
    }

    fun removeToke(){
        token = null
        SPFUtil.instance?.remove(Constants.SESSION_TOKEN)
    }

    fun logout(){
        user = null
        token = null
        SPFUtil.instance?.removeAll()
    }
}