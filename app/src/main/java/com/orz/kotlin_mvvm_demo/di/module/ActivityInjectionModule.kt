package com.orz.kotlin_mvvm_demo.di.module

import com.orz.kotlin_mvvm_demo.di.Scopes
import com.orz.kotlin_mvvm_demo.core.view.guide.GuideActivity
import com.orz.kotlin_mvvm_demo.core.view.main.MainActivity
import com.orz.kotlin_mvvm_demo.core.view.paging.RoleActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityInjectionModule {

    @Scopes.Activity
    @ContributesAndroidInjector
    internal abstract fun contributeGuideActivity(): GuideActivity

    @Scopes.Activity
    @ContributesAndroidInjector
    internal abstract fun contributeRoleActivity(): RoleActivity

    @Scopes.Activity
    @ContributesAndroidInjector(modules = [FragmentInjectionModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}