package com.oleksandrlysun.securemessenger.models

data class Chat(
    val id: Int,
    val me: User,
    val other: User,
    val messages: ArrayList<Message>
)