package com.orz.kotlin_mvvm_demo.network.form

import okhttp3.RequestBody

open class PaginatedForm : Form() {

    var page: Int = 0
    var limit: Int = 20

    override fun build(): RequestBody {
        add("page", page)
        add("limit", limit)
        return super.build()
    }
}