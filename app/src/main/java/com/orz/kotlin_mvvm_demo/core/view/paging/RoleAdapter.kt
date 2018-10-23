package com.orz.kotlin_mvvm_demo.core.view.paging

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.orz.kotlin_mvvm_demo.db.entity.RoleEntity

/**
 * @author: orz on 2018/9/21
 */
class RoleAdapter(private val mItemLayoutId: Int):PagedListAdapter<RoleEntity,RoleViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(mItemLayoutId, parent, false)
        return RoleViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoleViewHolder, position: Int) {
            holder.bindTo(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<RoleEntity>() {
            override fun areItemsTheSame(oldItem: RoleEntity, newItem: RoleEntity): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: RoleEntity, newItem: RoleEntity): Boolean =
                    oldItem == newItem
        }
    }
}