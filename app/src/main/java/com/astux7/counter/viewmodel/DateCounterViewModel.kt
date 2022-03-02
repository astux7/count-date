package com.astux7.counter.viewmodel

import android.content.res.Resources
import android.os.CountDownTimer
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.astux7.counter.R
import com.astux7.counter.utils.UserPrefManager
import com.astux7.counter.utils.showCountDownText
import java.text.SimpleDateFormat
import java.util.*


class DateCounterViewModel(userPref: UserPrefManager) : ViewModel() {
    private val userPref: UserPrefManager = userPref

    var leftTime: MutableState<String?> = mutableStateOf(null)
    var timeAdded: String? = null
    var titleText: String? = null

    init {
        getDate()
        getTitle()

        if (!timeAdded.isNullOrEmpty()) {
            start()
        }

    }

    fun save(date: String, text: String) {
        timeAdded = date
        titleText = text
        userPref.save(date, titleText)
        start()
    }

    private fun getDate() {
        timeAdded = userPref.readDate()
    }

    private fun getTitle() {
        titleText = userPref.readText()
    }

    private fun start() {
        val currentTime = Calendar.getInstance().time
        val format1 = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val endDate = format1.parse(timeAdded)

        var different = endDate.time - currentTime.time

        object : CountDownTimer(different, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                leftTime.value = millisUntilFinished.showCountDownText()
            }

            override fun onFinish() {
                leftTime.value = Resources.getSystem().getString(R.string.finished_label)
            }

        }.start()
    }
}