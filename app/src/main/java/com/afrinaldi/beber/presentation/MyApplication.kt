package com.afrinaldi.beber.presentation

import android.app.Application
import com.afrinaldi.beber.presentation.di.useCaseModule
import com.afrinaldi.beber.presentation.di.viewModelModule
import com.afrinaldi.core.di.databaseModule
import com.afrinaldi.core.di.networkModule
import com.afrinaldi.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}