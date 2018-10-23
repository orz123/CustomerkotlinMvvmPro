/**
 * Copyright 2016 JustWayward Team
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.orz.kotlin_mvvm_demo.config

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup

import com.orz.kotlin_mvvm_demo.R


/**
 * @author orz.
 * @date 16/8/9.
 */
object StatusBarCompat {
    private val INVALID_VAL = -1

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun compat(activity: Activity, statusColor: Int): View? {
        var color = ContextCompat.getColor(activity, R.color.colorPrimaryDark)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (statusColor != INVALID_VAL) {
                color = statusColor
            }
            activity.window.statusBarColor = color
            return null
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            val contentView = activity.findViewById<View>(android.R.id.content) as ViewGroup
            if (statusColor != INVALID_VAL) {
                color = statusColor
            }
            var statusBarView: View? = contentView.getChildAt(0)
            if (statusBarView != null && statusBarView.measuredHeight == getStatusBarHeight(activity)) {
                statusBarView.setBackgroundColor(color)
                return statusBarView
            }
            statusBarView = View(activity)
            val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight(activity))
            statusBarView.setBackgroundColor(color)
            contentView.addView(statusBarView, lp)
            return statusBarView
        }
        return null

    }

    fun compat(activity: Activity) {
        compat(activity, INVALID_VAL)
    }


    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}