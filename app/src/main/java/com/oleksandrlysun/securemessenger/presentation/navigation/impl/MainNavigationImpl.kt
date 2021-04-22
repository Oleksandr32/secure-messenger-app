package com.oleksandrlysun.securemessenger.presentation.navigation.impl

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavController.OnDestinationChangedListener
import com.oleksandrlysun.securemessenger.R
import com.oleksandrlysun.securemessenger.models.Chat
import com.oleksandrlysun.securemessenger.presentation.navigation.MainNavigation
import com.oleksandrlysun.securemessenger.presentation.screens.pincode.PinCodeMode

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

    override fun splashToLogin() {
        navController.navigate(R.id.action_splashFragment_to_loginFragment)
    }

    override fun splashToPinCode() {
        val bundle = bundleOf("mode" to PinCodeMode.ENTER)
        navController.navigate(R.id.action_splashFragment_to_pinCodeFragment, bundle)
    }

    override fun loginToRegister() {
        navController.navigate(R.id.action_loginFragment_to_registerFragment)
    }

    override fun loginToPinCode() {
        val bundle = bundleOf("mode" to PinCodeMode.CREATE)
        navController.navigate(R.id.action_loginFragment_to_pinCodeFragment, bundle)
    }

    override fun registerToPinCode() {
        val bundle = bundleOf("mode" to PinCodeMode.CREATE)
        navController.navigate(R.id.action_registerFragment_to_pinCodeFragment, bundle)
    }

    override fun pinCodeToChats() {
        navController.navigate(R.id.action_pinCodeFragment_to_chatsFragment)
    }

    override fun chatsToChat(chat: Chat) {
        val bundle = bundleOf("chat" to chat)
        navController.navigate(R.id.action_chatsFragment_to_chatFragment, bundle)
    }

    override fun chatsToCreateChat() {
        navController.navigate(R.id.action_chatsFragment_to_createChatFragment)
    }

    override fun createChatToChat(chat: Chat) {
        val bundle = bundleOf("chat" to chat)
        navController.navigate(R.id.action_createChatFragment_to_chatFragment, bundle)
    }
}