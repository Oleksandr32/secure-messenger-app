package com.oleksandrlysun.securemessenger.preferences.impl

import android.content.Context
import androidx.core.content.edit
import com.oleksandrlysun.securemessenger.preferences.*

class PreferencesImpl(context: Context) : Preferences {

    private val sharedPreferences =
        context.getSharedPreferences("${context::getPackageName}.preferences", Context.MODE_PRIVATE)

    override val userPreferences: UserPreferences by lazy {
        UserPreferencesImpl(sharedPreferences)
    }

    override fun clear() {
        sharedPreferences.edit(commit = true) { clear() }
    }
}