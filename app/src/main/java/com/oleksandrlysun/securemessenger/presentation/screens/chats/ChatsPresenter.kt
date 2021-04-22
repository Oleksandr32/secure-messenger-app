package com.oleksandrlysun.securemessenger.presentation.screens.chats

import com.oleksandrlysun.securemessenger.extensions.uiThread
import com.oleksandrlysun.securemessenger.interactors.chats.*
import com.oleksandrlysun.securemessenger.models.Chat
import com.oleksandrlysun.securemessenger.presentation.base.*
import com.oleksandrlysun.securemessenger.presentation.navigation.MainNavigation
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import java.util.*

class ChatsPresenter(
    view: ChatsView,
    private val navigation: MainNavigation,
    private val chatsInteractor: ChatsInteractor
) : Presenter<ChatsView>(view) {

    private lateinit var compositeDisposable: CompositeDisposable

    private var chats = LinkedList<Chat>()

    override fun onViewCreated(arguments: Arguments) {
        compositeDisposable = CompositeDisposable()
        listenChats()
        listenNewChat()
        chatsInteractor.subscribe(ChatsAction.GET)
    }

    override fun onDestroyView() {
        compositeDisposable.dispose()
    }

    fun onChatClick(chat: Chat) {
        navigation.chatsToChat(chat)
    }

    fun createChat() {
        navigation.chatsToCreateChat()
    }

    private fun listenChats() {
        chatsInteractor.observeChats()
            .map { LinkedList(it) }
            .uiThread()
            .subscribe {
                chats = it
                view.setChats(it)
            }
            .addTo(compositeDisposable)

    }

    private fun listenNewChat() {
        chatsInteractor.observeChat()
            .uiThread()
            .subscribe {
                chats.add(it)
                view.setChat(it)
            }
            .addTo(compositeDisposable)
    }
}