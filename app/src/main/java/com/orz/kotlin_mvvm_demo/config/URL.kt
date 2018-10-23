package com.orz.kotlin_mvvm_demo.config

/**
 * @author：orz on 18/5/7 15:20
 */
object URL {
    private const val isRelease = true//true 正式发布
    private const val RELEASE_URL = "https://hshop.chinambgame.com/"
    private const val DEBUG_URL = "http://192.168.0.122:3006/"
    val BASE_URL = if (isRelease) RELEASE_URL else DEBUG_URL

    const val GET_APP_VERSION_URL = "app_versions/update_version"//版本更新
}
