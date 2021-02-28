package com.oleksandrlysun.securemessenger.presentation.screens.createchat

import com.oleksandrlysun.securemessenger.interactors.chats.*
import com.oleksandrlysun.securemessenger.interactors.users.*
import com.oleksandrlysun.securemessenger.models.User
import com.oleksandrlysun.securemessenger.presentation.base.Presenter
import com.oleksandrlysun.securemessenger.presentation.navigation.MainNavigation
import kotlinx.coroutines.flow.collect

class CreateChatPresenter(
    view: CreateChatView,
    private val navigation: MainNavigation,
    private val usersInteractor: UsersInteractor,
    private val chatsInteractor: ChatsInteractor
) : Presenter<CreateChatView>(view) {

    private var users = emptyList<User>()

    override fun onViewCreated() {
        launchWithHandler {
            usersInteractor.observeUsers()
                .collect {
                    if (users != it) {
                        users = it
                        view.setUsers(it)
                    }
                }
        }
    }

    fun search(query: String) {
        if (query.isBlank()) {
            users = emptyList()
            view.setUsers(users)
            return
        }

        usersInteractor.subscribe(UsersAction.SEARCH, query)
    }

    fun createChat(user: User) {
        navigation.createChatToChat(user)
    }
}