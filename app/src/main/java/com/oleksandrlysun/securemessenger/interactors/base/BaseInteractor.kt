package com.oleksandrlysun.securemessenger.interactors.base

import com.oleksandrlysun.securemessenger.api.ApiService
import com.oleksandrlysun.securemessenger.models.Subscribe
import com.oleksandrlysun.securemessenger.preferences.UserPreferences
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseInteractor(
    protected val apiService: ApiService,
    protected val userPreferences: UserPreferences
) {

    protected abstract val channel: String

    fun observeEvents(): Flow<WebSocket.Event> {
        return apiService.observeEvents().flowOn(Dispatchers.IO)
    }

    protected fun subscribe(action: String, data: Any? = null) {
        val subscribe = Subscribe(
            userId = userPreferences.userId,
            channel = channel,
            action = action,
            data = data
        )
        apiService.subscribe(subscribe)
    }
}