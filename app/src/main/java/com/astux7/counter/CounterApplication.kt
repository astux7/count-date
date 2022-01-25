package com.astux7.counter

import android.app.Application
import com.astux7.counter.di.CounterModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CounterApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(this@CounterApplication)
            // declare modules
            modules(CounterModule.myModule)
        }
    }
}
