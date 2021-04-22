package com.oleksandrlysun.securemessenger.api

import com.oleksandrlysun.securemessenger.models.*
import com.tinder.scarlet.WebSocket
import io.reactivex.Flowable

interface ApiService {

    fun observeEvents(): Flowable<WebSocket.Event>

    fun subscribe(subscribe: Subscribe)

    fun observeUser(): Flowable<User>

    fun observeChats(): Flowable<List<Chat>>

    fun observeChat(): Flowable<Chat>

    fun observeMessages(): Flowable<List<Message>>

    fun observeMessage(): Flowable<Message>

    fun observeUsers(): Flowable<List<User>>

}