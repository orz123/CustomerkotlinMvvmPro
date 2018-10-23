package com.orz.kotlin_mvvm_demo.di.component

import com.orz.kotlin_mvvm_demo.di.module.ActivityInjectionModule
import com.orz.kotlin_mvvm_demo.di.module.AppInjectionModule
import com.orz.kotlin_mvvm_demo.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidInjectionModule::class,
            AppInjectionModule::class,
            ActivityInjectionModule::class
        ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(application: App)
}