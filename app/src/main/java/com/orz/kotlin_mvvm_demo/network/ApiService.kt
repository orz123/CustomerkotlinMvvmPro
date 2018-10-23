package com.orz.kotlin_mvvm_demo.network

import com.orz.kotlin_mvvm_demo.config.URL
import com.orz.kotlin_mvvm_demo.core.data.pojo.VersionInfo
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


/**
 * @author orz
 */
interface ApiService {
    @FormUrlEncoded
    @POST(URL.GET_APP_VERSION_URL)
    fun getVersionData(@Field("plat") plat: Int,
                       @Field("code") versionCode: Int): Observable<HttpResult<VersionInfo>>
}
