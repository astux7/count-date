package com.astux7.counter.viewmodel

import android.os.CountDownTimer
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astux7.counter.utils.UserPrefManager
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class DateCounterViewModel(userPref: UserPrefManager) : ViewModel() {
    private val userPref: UserPrefManager = userPref

    var leftTime: MutableState<String?> = mutableStateOf(null)
    var timeAdded: MutableState<String?> = mutableStateOf(null)

    init {
        getDate()
      //  viewModelScope.launch {
            if(!timeAdded.value.isNullOrEmpty()){
                start()
            }
      //  }
    }

    fun save(date: String) {
        timeAdded.value = date
        userPref.save(date)
        start()
    }

    private fun getDate() {
        timeAdded.value = userPref.read()
    }

    private fun start() {
        val currentTime = Calendar.getInstance().time
        val format1 = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val endDate = format1.parse(timeAdded.value)

        var different = endDate.time - currentTime.time
        object : CountDownTimer(different, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                var diff = millisUntilFinished
                val secondsInMilli: Long = 1000
                val minutesInMilli = secondsInMilli * 60
                val hoursInMilli = minutesInMilli * 60
                val daysInMilli = hoursInMilli * 24

                val elapsedDays = diff / daysInMilli
                diff %= daysInMilli

                val elapsedHours = diff / hoursInMilli
                diff %= hoursInMilli

                val elapsedMinutes = diff / minutesInMilli
                diff %= minutesInMilli

                val elapsedSeconds = diff / secondsInMilli

                leftTime.value =
                    "$elapsedDays days $elapsedHours hs $elapsedMinutes min $elapsedSeconds sec"
            }

            override fun onFinish() {
                leftTime.value = "Congratulations you did it! Now you free to do what you want!"
            }

        }.start()
    }
}