package com.oleksandrlysun.securemessenger.api.impl

import com.oleksandrlysun.securemessenger.api.*
import com.oleksandrlysun.securemessenger.models.*
import com.tinder.scarlet.WebSocket
import io.reactivex.Flowable

class ApiServiceImpl(private val scarletService: ScarletService) : ApiService {

    override fun observeEvents(): Flowable<WebSocket.Event> {
        return scarletService.observeEvents()
    }

    override fun subscribe(subscribe: Subscribe) {
        scarletService.subscribe(subscribe)
    }

    override fun observeUser(): Flowable<User> {
        return scarletService.observeUser()
    }

    override fun observeChats(): Flowable<List<Chat>> {
        return scarletService.observeChats()
    }

    override fun observeChat(): Flowable<Chat> {
        return scarletService.observeChat()
    }

    override fun observeMessages(): Flowable<List<Message>> {
        return scarletService.observeMessages()
    }

    override fun observeMessage(): Flowable<Message> {
        return scarletService.observeMessage()
    }

    override fun observeUsers(): Flowable<List<User>> {
        return scarletService.observeUsers()
    }
}