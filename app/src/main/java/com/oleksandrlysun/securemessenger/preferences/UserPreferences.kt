package com.oleksandrlysun.securemessenger.preferences

import com.oleksandrlysun.securemessenger.models.User

interface UserPreferences {
    var userId: Int

    var firstName: String?

    var lastName: String?

    var userName: String?

    var password: String?

    var pinCode: String?

    var publicKey: String?

    var privateKey: String?

    fun getUser(): User

    fun saveUser(user: User)
}