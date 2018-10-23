package com.orz.kotlin_mvvm_demo.core.data.pojo

/**
 * @author: orz on 2018/10/12
 * 用户信息实体类
 */
class UserEntity(var mobile: String? = "",
                 var level: Int = 0,
                 var status: Int? = 1,
                 var invite_code: String? = "",
                 var recharge_money: Float? = null,
                 var money_balance: Float? = null,
                 var egg_balance: Float? = null,
                 var reward_balance: Float? = null,
                 var hhm_balance: Float? = null,
                 var real_name: String? = "",
                 var link: String? = "",
                 var can_withdraw_money: Float? = null,
                 var withdraw_rate: Float? = 0.0f,
                 var withdraw_min: Float? = null)