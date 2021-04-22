package com.oleksandrlysun.securemessenger.preferences

interface Preferences {

    val userPreferences: UserPreferences

    val chatPreferences: ChatPreferences

    fun clear()
}