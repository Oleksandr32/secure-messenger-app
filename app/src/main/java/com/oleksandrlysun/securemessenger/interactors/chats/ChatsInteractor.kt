package com.oleksandrlysun.securemessenger.interactors.chats

import com.oleksandrlysun.securemessenger.api.ApiService
import com.oleksandrlysun.securemessenger.interactors.base.BaseInteractor
import com.oleksandrlysun.securemessenger.interactors.chats.ChatsAction.*
import com.oleksandrlysun.securemessenger.models.*
import com.oleksandrlysun.securemessenger.preferences.UserPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class ChatsInteractor(
    apiService: ApiService,
    userPreferences: UserPreferences
) : BaseInteractor(apiService, userPreferences) {

    override val channel = "chats"

    fun subscribe(action: ChatsAction, data: Any? = null) {
        subscribe(action.serialize(), data)
    }

    fun observeChats(): Flow<List<Chat>> {
        return apiService.observeChats().flowOn(Dispatchers.IO)
    }

    private fun ChatsAction.serialize(): String {
        return when (this) {
            GET -> "get"
        }
    }
}