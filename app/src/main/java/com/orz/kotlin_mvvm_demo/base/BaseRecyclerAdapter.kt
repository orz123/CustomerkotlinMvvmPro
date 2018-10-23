package com.orz.kotlin_mvvm_demo.base

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author orz on 17/6/6 12:08
 */
abstract class BaseRecyclerAdapter<T : Any>(var list: List<T>?, private val mItemLayoutId: Int) : RecyclerView.Adapter<BaseViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    private var onItemLongClickListener: OnItemLongClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(mItemLayoutId, parent, false)
        return BaseViewHolder(view)

    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int, payloads: List<Any>) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)

        } else {
            list?.let { it[position] }?.let { convert(holder, it, position, payloads) }
        }


    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        this.list?.let { it[position] }?.let { convert(holder, it, position, arrayListOf()) }
        holder.itemView.setOnClickListener { v ->
            onItemClickListener?.let {
                it.onItemClickListener(v, position)
            }
        }
        holder.itemView.setOnLongClickListener { v ->
            onItemLongClickListener?.let {
                it.onItemLongClickListener(v, position)
            }
            true
        }
    }

    protected abstract fun convert(holder: BaseViewHolder, t: T, position: Int, payloads: List<Any>)

    override fun getItemCount(): Int {
        return list?.size ?:0
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun setOnItemLongClickListener(onItemLongClickListener: OnItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener
    }

    interface OnItemClickListener {
        fun onItemClickListener(v: View, position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClickListener(v: View, position: Int)
    }
}

