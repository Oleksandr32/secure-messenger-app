package com.oleksandrlysun.securemessenger.api

import com.oleksandrlysun.securemessenger.models.*
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.flow.Flow

interface ApiService {

    fun observeEvents(): Flow<WebSocket.Event>

    fun subscribe(subscribe: Subscribe)

    fun observeChats(): Flow<List<Chat>>

    fun observeUsers(): Flow<List<User>>

}