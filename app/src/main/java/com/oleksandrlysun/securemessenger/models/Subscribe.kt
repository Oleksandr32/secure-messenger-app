package com.oleksandrlysun.securemessenger.models

data class Subscribe(
    var userId: Int,
    var channel: String,
    var action: String,
    var data: Any?
)