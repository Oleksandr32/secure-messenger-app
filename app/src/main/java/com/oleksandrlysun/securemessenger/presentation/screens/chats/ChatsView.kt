package com.oleksandrlysun.securemessenger.presentation.screens.chats

import com.oleksandrlysun.securemessenger.models.Chat
import com.oleksandrlysun.securemessenger.presentation.base.BaseView

interface ChatsView : BaseView {

    fun setChats(chats: List<Chat>)

    fun setChat(chat: Chat)
}