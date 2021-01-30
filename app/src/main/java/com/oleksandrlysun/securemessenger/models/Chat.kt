package com.oleksandrlysun.securemessenger.models

import android.graphics.drawable.Drawable

data class Chat(val avatar: Drawable, val name: String, val message: String, val date: String)