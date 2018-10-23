package com.orz.kotlin_mvvm_demo.util

import android.text.TextUtils
import java.text.ParseException
import java.text.SimpleDateFormat

object DateUtil {

    private val Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss"
    private val TARGET_FORMAT = "HH:mm:ss\nyyyy/MM/dd"
    private val DATE_FORMAT = SimpleDateFormat(TARGET_FORMAT)

    /**
     * @param time : 2018-08-23 05:58:01
     */
    fun format(time: String): String {
        if (TextUtils.isEmpty(time)) {
            return ""
        }
        try {
            val format = SimpleDateFormat(Y_M_D_H_M_S)
            val date = format.parse(time)
            return DATE_FORMAT.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return ""
    }
}
