package com.oleksandrlysun.securemessenger.api

import com.oleksandrlysun.securemessenger.models.*
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import io.reactivex.Flowable

interface ScarletService {

    @Receive
    fun observeEvents(): Flowable<WebSocket.Event>

    @Send
    fun subscribe(subscribe: Subscribe)

    @Receive
    fun observeUser(): Flowable<User>

    @Receive
    fun observeChats(): Flowable<List<Chat>>

    @Receive
    fun observeChat(): Flowable<Chat>

    @Receive
    fun observeMessages(): Flowable<List<Message>>

    @Receive
    fun observeMessage(): Flowable<Message>

    @Receive
    fun observeUsers(): Flowable<List<User>>
}