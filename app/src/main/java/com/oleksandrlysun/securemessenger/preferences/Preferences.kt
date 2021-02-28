package com.oleksandrlysun.securemessenger.preferences

interface Preferences {

    val userPreferences: UserPreferences

    fun clear()
}