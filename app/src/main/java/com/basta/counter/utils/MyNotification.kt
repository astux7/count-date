package com.basta.counter.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.basta.counter.di.Counter

class MyNotification : BroadcastReceiver() {
    override fun onReceive(context: Context?, p1: Intent?) {
        context?.let {
            val service = CounterNotificationService(it)
            service.showNotification(Counter.value)
        }
    }
}