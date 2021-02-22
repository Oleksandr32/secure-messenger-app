package com.oleksandrlysun.securemessenger.models

data class Chat(
    val id: Int,
    val name: String,
    val users: Pair<User, User>,
    val messages: ArrayList<Message>
)