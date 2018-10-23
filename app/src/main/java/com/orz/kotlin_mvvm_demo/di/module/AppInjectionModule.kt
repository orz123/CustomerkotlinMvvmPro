package com.orz.kotlin_mvvm_demo.di.module

import dagger.Module

@Module(includes = [
    ViewModelModule::class,
    DbModule::class,
    ApiServiceModule::class
])
class AppInjectionModule {

}