package com.oleksandrlysun.securemessenger.models

import org.joda.time.DateTime
import java.io.Serializable

data class Message(
    val id: Int,
    val chatId: Int,
    var body: String,
    val date: DateTime?,
    val isMine: Boolean
) : Serializable