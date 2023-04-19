package com.isea.hypotheticalsports

import android.app.Application
import com.isea.hypotheticalsports.di.repositoryModule
import com.isea.hypotheticalsports.di.retrofitModule
import com.isea.hypotheticalsports.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class SportsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@SportsApplication)
            modules(listOf(retrofitModule, repositoryModule, viewModelModule))
        }
    }
}