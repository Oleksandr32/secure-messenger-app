package com.oleksandrlysun.securemessenger.presentation.navigation.impl

import androidx.navigation.NavController
import androidx.navigation.NavController.OnDestinationChangedListener
import com.oleksandrlysun.securemessenger.R
import com.oleksandrlysun.securemessenger.models.User
import com.oleksandrlysun.securemessenger.presentation.navigation.MainNavigation

class MainNavigationImpl(private val navController: NavController) : MainNavigation {

    override fun subscribe(listener: OnDestinationChangedListener) {
        navController.addOnDestinationChangedListener(listener)
    }

    override fun unsubscribe(listener: OnDestinationChangedListener) {
        navController.removeOnDestinationChangedListener(listener)
    }

    override fun navigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun chatsToCreateChat() {
        navController.navigate(R.id.action_chatsFragment_to_createChatFragment)
    }

    override fun createChatToChat(user: User) {
        navigateUp()
    }
}