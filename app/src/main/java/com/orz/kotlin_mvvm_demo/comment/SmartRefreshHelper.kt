package com.orz.kotlin_mvvm_demo.comment


import android.content.Context

import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.orz.kotlin_mvvm_demo.R

/**
 * 全局设置SmartRefreshLayout
 */
object SmartRefreshHelper {

    fun setDefaultConfig() {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater { context: Context, layout: RefreshLayout ->
            layout.setPrimaryColorsId(R.color.white, R.color.dark)//全局设置主题颜色
            ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate)
        }
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater { context, layout -> ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate) }
    }
}

