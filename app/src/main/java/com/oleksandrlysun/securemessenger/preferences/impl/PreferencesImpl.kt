package com.oleksandrlysun.securemessenger.preferences.impl

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.oleksandrlysun.securemessenger.preferences.*

class PreferencesImpl(context: Context) : Preferences {

    private val sharedPreferences = EncryptedSharedPreferences.create(
        context,
        "${context::getPackageName}.encrypted_preferences",
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override val userPreferences: UserPreferences by lazy {
        UserPreferencesImpl(sharedPreferences)
    }

    override val chatPreferences: ChatPreferences by lazy {
        ChatPreferencesImpl(sharedPreferences)
    }

    override fun clear() {
        sharedPreferences.edit(commit = true) { clear() }
    }
}