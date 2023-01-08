package com.basta.counter

import BaseVM
import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.basta.counter.navigation.Directions
import com.basta.counter.navigation.buildTheGraph
import com.basta.counter.ui.theme.KickoffTheme
import com.basta.counter.utils.CounterNotificationService
import com.basta.counter.utils.MyNotification
import com.basta.counter.utils.UserPrefManager
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm: BaseVM = getViewModel()

        if(vm.getDate() != 0L) {
            scheduleNotification(vm.getDate())
        }
        setContent {
            KickoffTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Directions.home.name
                    ) {
                        buildTheGraph(navController)
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun scheduleNotification(date: Long) {
        val intent = Intent(applicationContext, MyNotification::class.java)

        val pendingIntent = PendingIntent.getBroadcast(applicationContext,
        1, intent, PendingIntent.FLAG_IMMUTABLE)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime(date)
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )
    }
// TODO look here how many minutes/ hours i want to make it work
    fun getTime(date: Long) : Long {
        val currentTime = date - 1000*60*60*1

        //return calendar.timeInMillis - currentTime > 0

        return currentTime
    }
}
