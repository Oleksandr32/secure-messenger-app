package com.oleksandrlysun.securemessenger.presentation.screens.chat

import com.oleksandrlysun.securemessenger.models.Chat
import com.oleksandrlysun.securemessenger.models.Message
import com.oleksandrlysun.securemessenger.presentation.base.BaseView

interface ChatView : BaseView {

    fun setChat(chat: Chat)

    fun setMessages(messages: List<Message>)

    fun setNewMessage(message: Message)

    fun onMessageSent()
}