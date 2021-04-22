package com.oleksandrlysun.securemessenger.models

import java.io.Serializable

enum class ChatStatus : Serializable {
    ACTIVE,
    PENDING,
    UNKNOWN
}