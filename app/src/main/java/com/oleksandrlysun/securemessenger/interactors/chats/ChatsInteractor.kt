package com.oleksandrlysun.securemessenger.interactors.chats

import com.oleksandrlysun.securemessenger.api.ApiService
import com.oleksandrlysun.securemessenger.extensions.ioThread
import com.oleksandrlysun.securemessenger.interactors.base.BaseInteractor
import com.oleksandrlysun.securemessenger.interactors.chats.ChatsAction.*
import com.oleksandrlysun.securemessenger.models.*
import com.oleksandrlysun.securemessenger.preferences.UserPreferences
import io.reactivex.Flowable

class ChatsInteractor(
    apiService: ApiService,
    userPreferences: UserPreferences
) : BaseInteractor(apiService, userPreferences) {

    override val channel = "chats"

    fun subscribe(action: ChatsAction, data: Any? = null) {
        subscribe(action.serialize(), data)
    }

    fun observeChats(): Flowable<List<Chat>> {
        return apiService.observeChats().ioThread()
    }

    fun observeChat(): Flowable<Chat> {
        return apiService.observeChat().ioThread()
    }

    private fun ChatsAction.serialize(): String {
        return when (this) {
            GET -> "get"
            CREATE -> "create"
        }
    }
}