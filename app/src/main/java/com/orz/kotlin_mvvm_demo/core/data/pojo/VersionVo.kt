package com.orz.kotlin_mvvm_demo.core.data.pojo

/**
 * @author: orz on 2018/10/13
 */
data class VersionVo(var name: String? = null,
                     var file_name: String? = null,
                     var file_url_name: String? = null,
                     var code: String? = null,
                     var remark: String? = null,
                     var plat: String? = null,
                     var force: Boolean? = null,
                     var need_update: Boolean? = false)