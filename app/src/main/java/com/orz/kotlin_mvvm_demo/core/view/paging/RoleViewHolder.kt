package com.orz.kotlin_mvvm_demo.core.view.paging

import android.view.View
import android.widget.TextView
import com.orz.kotlin_mvvm_demo.base.BaseViewHolder
import com.orz.kotlin_mvvm_demo.callback.BindData
import com.orz.kotlin_mvvm_demo.db.entity.RoleEntity
import org.jetbrains.anko.find
import com.orz.kotlin_mvvm_demo.R


/**
 * @author: orz on 2018/9/21
 */
class RoleViewHolder(convertView: View) : BaseViewHolder(convertView), BindData<RoleEntity> {

    override fun bindTo(t: RoleEntity?) {
        itemView.find<TextView>(R.id.tv_role_name).text = t?.name
    }
}