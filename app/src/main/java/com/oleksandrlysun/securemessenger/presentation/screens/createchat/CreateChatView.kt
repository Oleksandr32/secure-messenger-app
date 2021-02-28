package com.oleksandrlysun.securemessenger.presentation.screens.createchat

import com.oleksandrlysun.securemessenger.models.User
import com.oleksandrlysun.securemessenger.presentation.base.BaseView

interface CreateChatView : BaseView {

    fun setUsers(users: List<User>)
}