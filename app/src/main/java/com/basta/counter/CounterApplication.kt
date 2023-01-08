package com.basta.counter

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.basta.counter.di.CounterModule
import com.basta.counter.utils.CounterNotificationService
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
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CounterNotificationService.COUNTER_CHANNEL_ID,
                "Counter", // on off toggle in settings of app notification
                NotificationManager.IMPORTANCE_DEFAULT
            )

            channel.description = "Used for Counter app notification"
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }
}
