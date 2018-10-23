package com.orz.kotlin_mvvm_demo.di.module

import com.orz.kotlin_mvvm_demo.core.view.main.fragment.ContractFragment
import com.orz.kotlin_mvvm_demo.core.view.main.fragment.HomeFragment
import com.orz.kotlin_mvvm_demo.core.view.main.fragment.MineFragment
import com.orz.kotlin_mvvm_demo.core.view.main.fragment.WalletFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentInjectionModule {

    @ContributesAndroidInjector
    internal abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    internal abstract fun contributeWalletFragment(): WalletFragment

    @ContributesAndroidInjector
    internal abstract fun contributeContractFragment(): ContractFragment

    @ContributesAndroidInjector
    internal abstract fun contributeMineFragment(): MineFragment
}