package com.basta.counter.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.BigTextStyle
import com.basta.counter.MainActivity
import com.basta.counter.R

class CounterNotificationService(private val context: Context) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(time: Long) {
        val activityIntent = Intent(context, MainActivity::class.java)

        val activityPendingIntent = PendingIntent.getActivity(
            context, 1, activityIntent, if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.FLAG_IMMUTABLE
            } else 0
        )

        val notification = NotificationCompat.Builder(context, COUNTER_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Timer")
            .setContentText("It is almost finishing!")
            .setStyle(BigTextStyle())
            .setContentIntent(activityPendingIntent)
          //  .addAction()
            .build()

        notificationManager.notify(1, notification)

    }

    companion object {
        const val COUNTER_CHANNEL_ID = "counter_channel"

    }
}