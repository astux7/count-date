package com.astux7.counter.utils

import android.app.DatePickerDialog
import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

fun Long.toDate() : String {
    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return dateFormatter.format( Date(this))
}

fun Context.showTimePicker(onResult: (Long) -> Unit) {
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

fun Long.showCountDownText() : String {
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