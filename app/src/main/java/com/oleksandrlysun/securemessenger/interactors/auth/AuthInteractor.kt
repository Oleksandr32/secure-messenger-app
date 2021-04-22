package com.oleksandrlysun.securemessenger.interactors.auth

import com.oleksandrlysun.securemessenger.api.ApiService
import com.oleksandrlysun.securemessenger.crypto.DiffieHellman
import com.oleksandrlysun.securemessenger.extensions.ioThread
import com.oleksandrlysun.securemessenger.interactors.base.BaseInteractor
import com.oleksandrlysun.securemessenger.interactors.auth.AuthAction.*
import com.oleksandrlysun.securemessenger.models.User
import com.oleksandrlysun.securemessenger.preferences.UserPreferences
import io.reactivex.Flowable

class AuthInteractor(
    apiService: ApiService,
    userPreferences: UserPreferences
) : BaseInteractor(apiService, userPreferences) {

    override val channel = "auth"

    private var user: User? = null

    fun isAuthorized(): Boolean {
        val user = userPreferences.getUser()
        return user.id != -1
                && user.userName != null
                && user.password != null
                && user.pinCode != null
                && user.publicKey != null
    }

    fun login(username: String? = null, password: String? = null) {
        val keyPair = DiffieHellman.generateKeyPair()
        user = User(
            userName = username ?: userPreferences.userName,
            password = password ?: userPreferences.password,
            publicKey = DiffieHellman.encodePublicKey(keyPair.public)
        )
        userPreferences.privateKey = DiffieHellman.encodePrivateKey(keyPair.private)
        subscribe(LOGIN.serialize(), user)
    }

    fun register(user: User) {
        val keyPair = DiffieHellman.generateKeyPair()
        this.user = user.apply { publicKey = DiffieHellman.encodePublicKey(keyPair.public) }
        userPreferences.privateKey = DiffieHellman.encodePrivateKey(keyPair.private)
        subscribe(REGISTER.serialize(), user)
    }

    fun savePinCode(pinCode: String) {
        userPreferences.pinCode = pinCode
    }

    fun observeUser(): Flowable<User> {
        return apiService.observeUser()
            .ioThread()
            .doOnNext { saveUser(it) }
    }

    fun checkPinCode(pinCode: String) = userPreferences.pinCode == pinCode

    private fun saveUser(user: User) {
        userPreferences.saveUser(user)
    }

    private fun AuthAction.serialize(): String {
        return when (this) {
            LOGIN -> "login"
            REGISTER -> "register"
        }
    }
}