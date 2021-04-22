package com.oleksandrlysun.securemessenger.presentation.screens.createchat

import com.oleksandrlysun.securemessenger.extensions.uiThread
import com.oleksandrlysun.securemessenger.interactors.chats.*
import com.oleksandrlysun.securemessenger.interactors.users.*
import com.oleksandrlysun.securemessenger.models.User
import com.oleksandrlysun.securemessenger.presentation.base.*
import com.oleksandrlysun.securemessenger.presentation.navigation.MainNavigation
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class CreateChatPresenter(
    view: CreateChatView,
    private val navigation: MainNavigation,
    private val usersInteractor: UsersInteractor,
    private val chatsInteractor: ChatsInteractor
) : Presenter<CreateChatView>(view) {

    private val compositeDisposable = CompositeDisposable()

    private var users = emptyList<User>()

    override fun onViewCreated(arguments: Arguments) {
        listenUsers()
        listenNewChat()
    }

    override fun onDestroyView() {
        compositeDisposable.dispose()
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
        chatsInteractor.subscribe(ChatsAction.CREATE, user.id)
    }

    private fun listenUsers() {
        usersInteractor.observeUsers()
            .uiThread()
            .subscribe {
                if (users != it) {
                    users = it
                    view.setUsers(it)
                }
            }
            .addTo(compositeDisposable)
    }

    private fun listenNewChat() {
        chatsInteractor.observeChat()
            .uiThread()
            .subscribe { navigation.createChatToChat(it) }
            .addTo(compositeDisposable)

    }
}