package com.oleksandrlysun.securemessenger.interactors.users

import com.oleksandrlysun.securemessenger.api.ApiService
import com.oleksandrlysun.securemessenger.interactors.base.BaseInteractor
import com.oleksandrlysun.securemessenger.models.*
import com.oleksandrlysun.securemessenger.preferences.UserPreferences
import com.oleksandrlysun.securemessenger.interactors.users.UsersAction.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class UsersInteractor(
    apiService: ApiService,
    userPreferences: UserPreferences
) : BaseInteractor(apiService, userPreferences) {

    override val channel = "users"

    fun subscribe(action: UsersAction, data: Any? = null) {
        subscribe(action.serialize(), data)
    }

    fun observeUsers(): Flow<List<User>> {
        return apiService.observeUsers().flowOn(Dispatchers.IO)
    }

    private fun UsersAction.serialize(): String {
        return when (this) {
            SEARCH -> "search"
        }
    }
}