package com.oleksandrlysun.securemessenger.di

import com.oleksandrlysun.securemessenger.interactors.auth.AuthInteractor
import com.oleksandrlysun.securemessenger.interactors.chat.ChatInteractor
import com.oleksandrlysun.securemessenger.interactors.chats.ChatsInteractor
import com.oleksandrlysun.securemessenger.interactors.users.UsersInteractor
import org.koin.dsl.module

val interactorsModule = module {

    single { AuthInteractor(get(), get()) }

    single { UsersInteractor(get(), get()) }

    single { ChatsInteractor(get(), get()) }

    single { ChatInteractor(get(), get(), get()) }
}