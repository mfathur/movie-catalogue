package com.mfathurz.moviecatalogue

import android.app.Application
import com.mfathurz.moviecatalogue.core.di.databaseModule
import com.mfathurz.moviecatalogue.core.di.networkModule
import com.mfathurz.moviecatalogue.core.di.repositoryModule
import com.mfathurz.moviecatalogue.di.useCaseModule
import com.mfathurz.moviecatalogue.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}