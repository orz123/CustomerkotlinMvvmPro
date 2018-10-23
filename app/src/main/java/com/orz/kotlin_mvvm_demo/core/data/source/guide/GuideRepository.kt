package com.orz.kotlin_mvvm_demo.core.data.source.guide

import com.orz.kotlin_mvvm_demo.core.data.BaseRepository
import com.orz.kotlin_mvvm_demo.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author: orz on 2018/9/20
 */
@Singleton
class GuideRepository @Inject constructor(private val apiService: ApiService) : BaseRepository(){

}