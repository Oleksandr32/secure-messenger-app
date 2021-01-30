package com.oleksandrlysun.securemessenger.api.impl

import com.oleksandrlysun.securemessenger.api.ApiService
import com.oleksandrlysun.securemessenger.api.ScarletService
import com.oleksandrlysun.securemessenger.models.Chat
import kotlinx.coroutines.flow.Flow

class ApiServiceImpl(private val scarletService: ScarletService) : ApiService {

    override fun observeChats(): Flow<List<Chat>> {
        return scarletService.observeChats()
    }
}