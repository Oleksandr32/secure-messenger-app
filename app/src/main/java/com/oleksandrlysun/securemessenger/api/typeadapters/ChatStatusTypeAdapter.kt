package com.oleksandrlysun.securemessenger.api.typeadapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.oleksandrlysun.securemessenger.models.ChatStatus

class ChatStatusTypeAdapter : TypeAdapter<ChatStatus>() {
    override fun write(out: JsonWriter, value: ChatStatus) {
        when (value) {
            ChatStatus.ACTIVE -> out.value(ACTIVE_STATUS)
            ChatStatus.PENDING -> out.value(PENDING_STATUS)
            ChatStatus.UNKNOWN -> out.nullValue()
        }
    }

    override fun read(input: JsonReader): ChatStatus {
        return when (input.nextString()) {
            ACTIVE_STATUS -> ChatStatus.ACTIVE
            PENDING_STATUS -> ChatStatus.PENDING
            else -> ChatStatus.UNKNOWN
        }
    }

    companion object {
        private const val ACTIVE_STATUS = "active"
        private const val PENDING_STATUS = "pending"
    }
}