package com.oleksandrlysun.securemessenger.api.impl

import com.oleksandrlysun.securemessenger.api.*
import com.oleksandrlysun.securemessenger.models.*
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.flow.Flow

class ApiServiceImpl(private val scarletService: ScarletService) : ApiService {

    override fun observeEvents(): Flow<WebSocket.Event> {
        return scarletService.observeEvents()
    }

    override fun subscribe(subscribe: Subscribe) {
        scarletService.subscribe(subscribe)
    }

    override fun observeChats(): Flow<List<Chat>> {
        return scarletService.observeChats()
    }

    override fun observeUsers(): Flow<List<User>> {
        return scarletService.observeUsers()
    }
}