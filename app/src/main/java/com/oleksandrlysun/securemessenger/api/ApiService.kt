package com.oleksandrlysun.securemessenger.api

import com.oleksandrlysun.securemessenger.models.Chat
import kotlinx.coroutines.flow.Flow

interface ApiService {

    fun observeChats(): Flow<List<Chat>>
}