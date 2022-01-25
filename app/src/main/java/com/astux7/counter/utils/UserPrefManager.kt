package com.astux7.counter.utils

import android.app.Application
import android.content.SharedPreferences
import android.content.Context.MODE_PRIVATE

class UserPrefManager(application: Application) {
    // MY_PREFS_NAME - a static String variable like:
    private val MY_PREFS_NAME = "MyLeaveDate"
    private var sharedPref: SharedPreferences = application.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)

    fun save(text: String) {
        val editor = sharedPref.edit()
        editor.putString("date", text)
        editor.apply()
    }

    fun read() = sharedPref.getString("date", "")
}