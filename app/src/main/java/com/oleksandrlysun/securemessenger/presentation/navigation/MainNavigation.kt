package com.oleksandrlysun.securemessenger.presentation.navigation

import androidx.navigation.NavController.OnDestinationChangedListener
import com.oleksandrlysun.securemessenger.models.Chat

interface MainNavigation {

    fun subscribe(listener: OnDestinationChangedListener)

    fun unsubscribe(listener: OnDestinationChangedListener)

    fun navigateUp(): Boolean

    fun splashToLogin()

    fun splashToPinCode()

    fun loginToRegister()

    fun loginToPinCode()

    fun registerToPinCode()

    fun pinCodeToChats()

    fun chatsToChat(chat: Chat)

    fun chatsToCreateChat()

    fun createChatToChat(chat: Chat)
}