package com.oleksandrlysun.securemessenger.models

import java.io.Serializable

data class User(
    var id: Int? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var userName: String? = null,
    var password: String? = null,
    var pinCode: String? = null,
    var publicKey: String? = null,
    var privateKey: String? = null
) : Serializable {

    val fullName: String
        get() = "$firstName $lastName"
}