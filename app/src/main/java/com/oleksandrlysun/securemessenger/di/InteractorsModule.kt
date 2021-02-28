package com.oleksandrlysun.securemessenger.di

import com.oleksandrlysun.securemessenger.interactors.chats.ChatsInteractor
import com.oleksandrlysun.securemessenger.interactors.users.UsersInteractor
import org.koin.dsl.module

val interactorsModule = module {

    single { UsersInteractor(get(), get()) }

    single { ChatsInteractor(get(), get()) }
}