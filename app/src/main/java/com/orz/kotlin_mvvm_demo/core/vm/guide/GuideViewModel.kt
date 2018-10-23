package com.orz.kotlin_mvvm_demo.core.vm.guide

import android.arch.lifecycle.ViewModel
import com.orz.kotlin_mvvm_demo.di.Scopes
import com.orz.kotlin_mvvm_demo.core.data.source.guide.GuideRepository
import javax.inject.Inject

/**
 * @author: orz on 2018/9/20
 */
@Scopes.Activity
class GuideViewModel
@Inject
constructor(private val repository: GuideRepository) : ViewModel(){

}