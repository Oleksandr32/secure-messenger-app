package com.oleksandrlysun.securemessenger.api

import com.oleksandrlysun.securemessenger.models.Chat
import com.tinder.scarlet.ws.Receive
import kotlinx.coroutines.flow.Flow

interface ScarletService {

    @Receive
    fun observeChats(): Flow<List<Chat>>
}