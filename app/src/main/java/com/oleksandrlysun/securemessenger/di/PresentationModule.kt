package com.oleksandrlysun.securemessenger.di

import androidx.navigation.findNavController
import com.oleksandrlysun.securemessenger.R
import com.oleksandrlysun.securemessenger.presentation.navigation.MainNavigation
import com.oleksandrlysun.securemessenger.presentation.navigation.impl.MainNavigationImpl
import com.oleksandrlysun.securemessenger.presentation.screens.MainActivity
import com.oleksandrlysun.securemessenger.presentation.screens.chat.*
import com.oleksandrlysun.securemessenger.presentation.screens.chats.*
import com.oleksandrlysun.securemessenger.presentation.screens.createchat.*
import com.oleksandrlysun.securemessenger.presentation.screens.login.LoginFragment
import com.oleksandrlysun.securemessenger.presentation.screens.login.LoginPresenter
import com.oleksandrlysun.securemessenger.presentation.screens.pincode.PinCodeFragment
import com.oleksandrlysun.securemessenger.presentation.screens.pincode.PinCodePresenter
import com.oleksandrlysun.securemessenger.presentation.screens.register.RegisterFragment
import com.oleksandrlysun.securemessenger.presentation.screens.register.RegisterPresenter
import com.oleksandrlysun.securemessenger.presentation.screens.splash.SplashFragment
import com.oleksandrlysun.securemessenger.presentation.screens.splash.SplashPresenter
import org.koin.dsl.module

val presentationModule = module {

    scope<MainActivity> {
        scoped<MainNavigation> {
            val navController = get<MainActivity>().findNavController(R.id.root_navigation_host_fragment)
            MainNavigationImpl(navController)
        }
    }

    scope<SplashFragment> {
        scoped { SplashPresenter(get(), get(), get()) }
    }

    scope<LoginFragment> {
        scoped { LoginPresenter(get(), get(), get()) }
    }

    scope<RegisterFragment> {
        scoped { RegisterPresenter(get(), get(), get()) }
    }

    scope<PinCodeFragment> {
        scoped { PinCodePresenter(get(), get(), get()) }
    }

    scope<ChatsFragment> {
        scoped { ChatsPresenter(get(), get(), get()) }
    }

    scope<CreateChatFragment> {
        scoped { CreateChatPresenter(get(), get(), get(), get()) }
    }

    scope<ChatFragment> {
        scoped { ChatPresenter(get(), get()) }
    }
}