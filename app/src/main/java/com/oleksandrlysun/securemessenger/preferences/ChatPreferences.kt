package com.oleksandrlysun.securemessenger.preferences

import com.oleksandrlysun.securemessenger.models.ChatSecret

interface ChatPreferences {

    fun getSecretKeys(): ArrayList<ChatSecret>

    fun saveSecretKeys(secrets: ArrayList<ChatSecret>)
}