package com.oleksandrlysun.securemessenger.interactors.users

import com.oleksandrlysun.securemessenger.api.ApiService
import com.oleksandrlysun.securemessenger.extensions.ioThread
import com.oleksandrlysun.securemessenger.interactors.base.BaseInteractor
import com.oleksandrlysun.securemessenger.models.*
import com.oleksandrlysun.securemessenger.preferences.UserPreferences
import com.oleksandrlysun.securemessenger.interactors.users.UsersAction.*
import io.reactivex.Flowable

class UsersInteractor(
    apiService: ApiService,
    userPreferences: UserPreferences
) : BaseInteractor(apiService, userPreferences) {

    override val channel = "users"

    fun subscribe(action: UsersAction, data: Any? = null) {
        subscribe(action.serialize(), data)
    }

    fun observeUsers(): Flowable<List<User>> {
        return apiService.observeUsers().ioThread()
    }

    private fun UsersAction.serialize(): String {
        return when (this) {
            SEARCH -> "search"
        }
    }
}