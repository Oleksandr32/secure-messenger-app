package com.oleksandrlysun.securemessenger.preferences.impl

import android.content.SharedPreferences
import com.oleksandrlysun.securemessenger.preferences.UserPreferences

class UserPreferencesImpl(private val sharedPreferences: SharedPreferences) : UserPreferences {

    companion object {
        const val KEY_USER_ID = "user-id"
    }

    override var userId: Int
        get() = 0 //sharedPreferences.getInt(KEY_USER_ID, -1)
        set(value) = sharedPreferences.edit().putInt(KEY_USER_ID, value).apply()


}