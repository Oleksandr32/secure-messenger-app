package com.oleksandrlysun.securemessenger.presentation.navigation

import androidx.navigation.NavController.OnDestinationChangedListener
import com.oleksandrlysun.securemessenger.models.User

interface MainNavigation {

    fun subscribe(listener: OnDestinationChangedListener)

    fun unsubscribe(listener: OnDestinationChangedListener)

    fun navigateUp(): Boolean

    fun chatsToCreateChat()

    fun createChatToChat(user: User)
}