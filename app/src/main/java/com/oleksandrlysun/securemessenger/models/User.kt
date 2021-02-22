package com.oleksandrlysun.securemessenger.models

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val userName: String,
    val chats: ArrayList<Chat>
)