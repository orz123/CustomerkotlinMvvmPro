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
package com.orz.kotlin_mvvm_demo.util

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * 软键盘工具类
 *
 * @author orz.
 * @date 16/4/9.
 */
@TargetApi(Build.VERSION_CODES.CUPCAKE)
object IMEUtils {

    /**
     * 切换键盘显示/隐藏状态
     *
     * @param context
     */
    fun toggleSoftInput(context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    /**
     * 显示键盘
     *
     * @param view
     * @return
     */
    fun showSoftInput(view: View): Boolean {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return imm.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }

    fun showSoftInput(activity: Activity): Boolean {
        val view = activity.currentFocus
        if (view != null) {
            val imm = view.context.getSystemService(
                    Context.INPUT_METHOD_SERVICE) as InputMethodManager
            return imm.showSoftInput(view, InputMethodManager.SHOW_FORCED)
        }
        return false
    }

    /**
     * 隐藏键盘
     *
     * @param view
     * @return
     */
    fun hideSoftInput(view: View): Boolean {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun hideSoftInput(activity: Activity): Boolean {
        if (activity.currentFocus != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            return imm.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
        }
        return false
    }

    /**
     * 判断键盘是否打开
     *
     * @param context
     * @return
     */
    fun isActive(context: Context): Boolean {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return imm.isActive
    }
}
