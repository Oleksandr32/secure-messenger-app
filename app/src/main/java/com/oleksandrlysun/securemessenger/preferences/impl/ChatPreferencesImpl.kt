package com.oleksandrlysun.securemessenger.preferences.impl

import android.content.SharedPreferences
import com.google.gson.Gson
import com.oleksandrlysun.securemessenger.models.ChatSecret
import com.oleksandrlysun.securemessenger.preferences.ChatPreferences
import com.google.gson.reflect.TypeToken


class ChatPreferencesImpl(private val sharedPreferences: SharedPreferences) : ChatPreferences {

    companion object {
        const val KEY_SECRET_KEYS = "secret-keys"
    }

    private val gson = Gson()

    override fun getSecretKeys(): ArrayList<ChatSecret> {
        val secrets = arrayListOf<ChatSecret>()
        val jsonString = sharedPreferences.getString(KEY_SECRET_KEYS, null)
        if (jsonString != null) {
            val type = object : TypeToken<ArrayList<ChatSecret>>() {}.type
            val savedSecrets = gson.fromJson<ArrayList<ChatSecret>>(jsonString, type)
            secrets.addAll(savedSecrets)
        }
        return secrets
    }

    override fun saveSecretKeys(secrets: ArrayList<ChatSecret>) {
        val secretsString = gson.toJson(secrets)
        sharedPreferences.edit().putString(KEY_SECRET_KEYS, secretsString).apply()
    }
}