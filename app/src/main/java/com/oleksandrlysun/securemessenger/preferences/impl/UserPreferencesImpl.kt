package com.oleksandrlysun.securemessenger.preferences.impl

import android.content.SharedPreferences
import com.oleksandrlysun.securemessenger.models.User
import com.oleksandrlysun.securemessenger.preferences.UserPreferences

class UserPreferencesImpl(private val sharedPreferences: SharedPreferences) : UserPreferences {

    companion object {
        const val KEY_USER_ID = "user-id"
        const val KEY_FIRST_NAME = "first-name"
        const val KEY_LAST_NAME = "last-name"
        const val KEY_USER_NAME = "user-name"
        const val KEY_PASSWORD = "password"
        const val KEY_PIN_CODE = "pinCode"
        const val KEY_PUBLIC_KEY = "public-key"
        const val KEY_PRIVATE_KEY = "private-key"
    }

    override var userId: Int
        get() = sharedPreferences.getInt(KEY_USER_ID, -1)
        set(value) = sharedPreferences.edit().putInt(KEY_USER_ID, value).apply()

    override var firstName: String?
        get() = sharedPreferences.getString(KEY_FIRST_NAME, null)
        set(value) = sharedPreferences.edit().putString(KEY_FIRST_NAME, value).apply()
    override var lastName: String?
        get() = sharedPreferences.getString(KEY_LAST_NAME, null)
        set(value) = sharedPreferences.edit().putString(KEY_LAST_NAME, value).apply()
    override var userName: String?
        get() = sharedPreferences.getString(KEY_USER_NAME, null)
        set(value) = sharedPreferences.edit().putString(KEY_USER_NAME, value).apply()
    override var password: String?
        get() = sharedPreferences.getString(KEY_PASSWORD, null)
        set(value) = sharedPreferences.edit().putString(KEY_PASSWORD, value).apply()
    override var pinCode: String?
        get() = sharedPreferences.getString(KEY_PIN_CODE, null)
        set(value) = sharedPreferences.edit().putString(KEY_PIN_CODE, value).apply()

    override var publicKey: String?
        get() = sharedPreferences.getString(KEY_PUBLIC_KEY, null)
        set(value) = sharedPreferences.edit().putString(KEY_PUBLIC_KEY, value).apply()

    override var privateKey: String?
        get() = sharedPreferences.getString(KEY_PRIVATE_KEY, null)
        set(value) = sharedPreferences.edit().putString(KEY_PRIVATE_KEY, value).apply()

    override fun getUser(): User {
        return User(userId, firstName, lastName, userName, password, pinCode, publicKey, privateKey)
    }

    override fun saveUser(user: User) {
        userId = user.id ?: userId
        firstName = user.firstName ?: firstName
        lastName = user.lastName ?: lastName
        userName = user.userName ?: userName
        password = user.password ?: password
        pinCode = user.pinCode ?: pinCode
        publicKey = user.publicKey ?: publicKey
        privateKey = user.privateKey ?: privateKey
    }
}