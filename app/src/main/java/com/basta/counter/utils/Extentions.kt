package com.basta.counter.utils

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.util.Log
import java.lang.System.console
import java.text.SimpleDateFormat
import java.util.*


fun Long.toDate(): String {
    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return dateFormatter.format(Date(this))
}

fun Long.toTime(): String {
    val dateFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    return dateFormatter.format(Date(this))
}

fun Context.showDatePicker(onResult: (Long) -> Unit) {
    val calendar = Calendar.getInstance()

    val listener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
        calendar[Calendar.MONTH] = month
        calendar[Calendar.YEAR] = year


        onResult(calendar.timeInMillis)
    }

    val timePickerDialog = DatePickerDialog(
        this,
        listener,
        calendar[Calendar.YEAR],
        calendar[Calendar.MONTH],
        calendar[Calendar.DAY_OF_MONTH]
    )

    timePickerDialog.show()
}

fun Context.showTimePicker(onResult: (Long) -> Unit) {
    val calendar = Calendar.getInstance()

    val listener = TimePickerDialog.OnTimeSetListener { _, hours, minutes ->
        calendar[Calendar.HOUR_OF_DAY] = hours
        calendar[Calendar.MINUTE] = minutes

        onResult(calendar.timeInMillis)
    }

    val timePicker = TimePickerDialog(
        this,
        listener,
        calendar[Calendar.HOUR_OF_DAY],
        calendar[Calendar.MINUTE],
        false
    )
    timePicker.show()

}

fun Long.showCountDownText(): String {
    var diff = this
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

    return "$elapsedDays days $elapsedHours hours $elapsedMinutes minutes $elapsedSeconds seconds"
}

fun String.currentTimeDifference(): Long {
    val currentTime = Calendar.getInstance().timeInMillis
   // val format1 = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val endDate = this.toLong()

    return endDate - currentTime
}

fun String.toFullDate(time: String?) : String {

    val format1 = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val endDate = format1.parse(this)
    val format2 = SimpleDateFormat("HH:mm", Locale.getDefault())
    val endTime = format2.parse(time)
    endDate.hours = endTime.hours
    endDate.minutes = endTime.minutes
 //   endDate.setUTCMinutes(timeSplit.get(1))

    Log.e("ASTA", endDate.toString() + " [  ] ")
    return ""
}