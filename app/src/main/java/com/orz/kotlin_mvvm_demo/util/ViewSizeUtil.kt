package com.orz.kotlin_mvvm_demo.util

import android.view.View
import android.view.ViewTreeObserver
import com.orz.kotlin_mvvm_demo.core.data.pojo.ViewMeasureBo


/**
 * @author: orz on 2018/10/27
 * 测量View 宽高工具类
 */
object ViewSizeUtil {
    fun getViewMeasureSize(view:View): ViewMeasureBo {
        val viewMeasureBo: ViewMeasureBo by lazy { ViewMeasureBo() }
        view.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                view.viewTreeObserver.removeOnGlobalLayoutListener(this)
                viewMeasureBo.measureWidth = view.measuredWidth
                viewMeasureBo.measureHeight = view.measuredHeight
            }
        })
        return viewMeasureBo
    }
}