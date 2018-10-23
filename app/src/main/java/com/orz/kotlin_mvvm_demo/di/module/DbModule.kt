package com.orz.kotlin_mvvm_demo.di.module

import android.arch.persistence.room.Room
import com.orz.kotlin_mvvm_demo.App
import com.orz.kotlin_mvvm_demo.db.CustomerDb
import com.orz.kotlin_mvvm_demo.db.DbConstants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author: orz on 2018/9/20
 */
@Module
class DbModule {

    @Singleton
    @Provides
    internal fun provideDb(app: App): CustomerDb {
        return Room.databaseBuilder(app, CustomerDb::class.java, DbConstants.DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}