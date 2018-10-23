package com.orz.kotlin_mvvm_demo.di

import javax.inject.Scope

interface Scopes {
    @MustBeDocumented
    @Scope
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Activity

    @MustBeDocumented
    @Scope
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Service

    @MustBeDocumented
    @Scope
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Receiver
}