package com.basta.counter.utils

import android.app.Application
import android.content.SharedPreferences
import android.content.Context.MODE_PRIVATE
import android.util.Log

class UserPrefManager(application: Application) {
    // MY_PREFS_NAME - a static String variable like:
    private val MY_PREFS_NAME = "MyKickOffDate"
    private var sharedPref: SharedPreferences = application.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)

    fun save(date: String, text: String? = "") {
        val editor = sharedPref.edit()
        Log.e("ASTA", "Time + ${date}")
        editor.putString("date", date)
        editor.putString("text", text)
        editor.apply()
    }

    fun readDate() = sharedPref.getString("date", "") ?: ""

    fun readText() = sharedPref.getString("text", "")
}