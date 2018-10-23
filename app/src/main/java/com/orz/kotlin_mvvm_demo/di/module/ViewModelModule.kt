package com.orz.kotlin_mvvm_demo.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.orz.kotlin_mvvm_demo.comment.vm.CustomerViewModelFactory
import com.orz.kotlin_mvvm_demo.core.vm.MainViewModel
import com.orz.kotlin_mvvm_demo.core.vm.guide.GuideViewModel
import com.orz.kotlin_mvvm_demo.core.vm.role.RoleViewModel
import com.orz.kotlin_mvvm_demo.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: CustomerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindLoginViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GuideViewModel::class)
    internal abstract fun bindGuideViewModel(viewModel: GuideViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RoleViewModel::class)
    internal abstract fun bindRoleViewModel(viewModel: RoleViewModel): ViewModel

}