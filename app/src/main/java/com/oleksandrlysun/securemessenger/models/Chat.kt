package com.oleksandrlysun.securemessenger.models

import java.io.Serializable

data class Chat(
    val id: Int,
    val me: User,
    val other: User,
    val status: ChatStatus,
    val lastMessage: Message
) : Serializable