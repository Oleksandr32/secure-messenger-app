package com.oleksandrlysun.securemessenger.di

import androidx.navigation.findNavController
import com.oleksandrlysun.securemessenger.R
import com.oleksandrlysun.securemessenger.presentation.navigation.MainNavigation
import com.oleksandrlysun.securemessenger.presentation.navigation.impl.MainNavigationImpl
import com.oleksandrlysun.securemessenger.presentation.screens.MainActivity
import com.oleksandrlysun.securemessenger.presentation.screens.chats.*
import com.oleksandrlysun.securemessenger.presentation.screens.createchat.*
import org.koin.dsl.module

val presentationModule = module {

    scope<MainActivity> {
        scoped<MainNavigation> {
            val navController = get<MainActivity>().findNavController(R.id.root_navigation_host_fragment)
            MainNavigationImpl(navController)
        }
    }

    scope<ChatsFragment> {
        scoped { ChatsPresenter(get(), get(), get()) }
    }

    scope<CreateChatFragment> {
        scoped { CreateChatPresenter(get(), get(), get(), get()) }
    }
}