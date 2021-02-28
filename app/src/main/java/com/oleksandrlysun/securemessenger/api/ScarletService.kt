package com.oleksandrlysun.securemessenger.api

import com.oleksandrlysun.securemessenger.models.*
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.flow.Flow

interface ScarletService {

    @Receive
    fun observeEvents(): Flow<WebSocket.Event>

    @Send
    fun subscribe(subscribe: Subscribe)

    @Receive
    fun observeChats(): Flow<List<Chat>>

    @Receive
    fun observeUsers(): Flow<List<User>>
}