package com.orz.kotlin_mvvm_demo.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * @author：orz  on 18/7/23 11:33
 */
object NetworkUtils {

    /**
     * 网络是否可用
     *
     * @param context
     * @return
     */
    fun isNetworkAvailable(context: Context): Boolean {
        val mgr = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = mgr.allNetworkInfo
        if (info != null) {
            for (i in info.indices) {
                if (info[i].state == NetworkInfo.State.CONNECTED) {
                    return true
                }
            }
        }
        return false
    }

}
