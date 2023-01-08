package com.basta.counter.utils

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class MyCalender() {
    private val calendar: Calendar = Calendar.getInstance()

    fun showDatePicker(context: Context, onResult: (String) -> Unit) {
        val listener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
            calendar[Calendar.MONTH] = month
            calendar[Calendar.YEAR] = year

         //   calendar.time

            onResult(toDate(calendar.timeInMillis))
         //   onResult(calendar.timeInMillis)
        }

        val timePickerDialog = DatePickerDialog(
            context,
            listener,
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        )

        timePickerDialog.show()
    }

    fun toDate(date: Long) :String{
        val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormatter.format(Date(date))
    }

    fun toTime(time:Long) : String {
        val dateFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
        return dateFormatter.format(Date(time))
    }

    fun showTimePicker(context: Context, onResult: (String) -> Unit) {
        val listener = TimePickerDialog.OnTimeSetListener { _, hours, minutes ->
            calendar[Calendar.HOUR_OF_DAY] = hours
            calendar[Calendar.MINUTE] = minutes

            onResult(toTime(calendar.timeInMillis))
        }

        val timePicker = TimePickerDialog(
            context,
            listener,
            calendar[Calendar.HOUR_OF_DAY],
            calendar[Calendar.MINUTE],
            false
        )
        timePicker.show()

    }

    fun toFullDate() : String {

        val dateFormatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val result = dateFormatter.format(Date(calendar.timeInMillis))
        return  result
    }

    fun toFullDateWithTime() : Long {
        return  calendar.timeInMillis
    }

    fun currentTimeDifference(): Boolean {
        val currentTime = Calendar.getInstance().timeInMillis

        return calendar.timeInMillis - currentTime > 0
    }


}