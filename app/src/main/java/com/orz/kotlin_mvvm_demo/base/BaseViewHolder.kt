package com.orz.kotlin_mvvm_demo.base

import android.support.annotation.IdRes
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View

/**
 * author：orz on 18/3/14 11:37
 */
open class BaseViewHolder(convertView: View) : RecyclerView.ViewHolder(convertView) {
    private var views = SparseArray<View>()

    fun <T : View> getView(@IdRes viewId: Int): T {
        var view: View? = views.get(viewId)
        if (view == null) {
            view = itemView.findViewById(viewId)
            views.put(viewId, view)
        }
        return (view as T?)!!
    }
}
