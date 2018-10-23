package com.orz.kotlin_mvvm_demo.comment.type

/**
 * @author: orz on 2018/10/8
 * 支付方式Type
 */
enum class PayType(val code:Int, val describe:String) {
    WX_PAY(1,"微信支付"),
    ALI_PAY(2,"支付宝支付"),
    WALLET_PAY(3,"银联支付")
}