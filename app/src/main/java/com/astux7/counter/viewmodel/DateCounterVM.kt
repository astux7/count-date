package com.astux7.counter.viewmodel

import android.app.Application
import android.os.CountDownTimer
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.astux7.counter.R
import com.astux7.counter.utils.UserPrefManager
import com.astux7.counter.utils.currentTimeDifference
import com.astux7.counter.utils.showCountDownText

class DateCounterVM(userPref: UserPrefManager, application: Application) :
    AndroidViewModel(application) {
    private val userPref: UserPrefManager = userPref

    var leftTime: MutableState<String?> = mutableStateOf(null)
    var timeAdded: String? = null
    var titleText: String? = null

    init {
        getData()
        start()
    }

    fun save(date: String, text: String) {
        timeAdded = date
        titleText = text
        userPref.save(date, titleText)
        start()
    }

    private fun getData() {
        timeAdded = userPref.readDate()
        titleText = userPref.readText()
    }

    fun validateDate(date: String?): Boolean {
        if (date.isNullOrBlank())
            return false

        return date.currentTimeDifference() > 0
    }

    fun timeEnded() = if (timeAdded.isNullOrBlank()) {
        false
    } else {
        timeAdded!!.currentTimeDifference() < 0
    }

    private fun start() {
        if (timeAdded.isNullOrEmpty())
            return

        val different = timeAdded!!.currentTimeDifference()

        object : CountDownTimer(different, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                leftTime.value = getApplication<Application>().resources.getString(
                    R.string.time_remain_label,
                    millisUntilFinished.showCountDownText()
                )
            }

            override fun onFinish() {
                leftTime.value =
                    getApplication<Application>().resources.getString(R.string.finished_label)
            }

        }.start()
    }
}