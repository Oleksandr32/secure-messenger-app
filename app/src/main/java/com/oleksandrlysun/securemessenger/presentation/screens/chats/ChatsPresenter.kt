package com.oleksandrlysun.securemessenger.presentation.screens.chats

import com.oleksandrlysun.securemessenger.interactors.chats.ChatsAction
import com.oleksandrlysun.securemessenger.interactors.chats.ChatsInteractor
import com.oleksandrlysun.securemessenger.presentation.base.Presenter
import com.oleksandrlysun.securemessenger.presentation.navigation.MainNavigation
import com.tinder.scarlet.WebSocket.Event.*
import kotlinx.coroutines.flow.collect

class ChatsPresenter(
    view: ChatsView,
    private val chatsInteractor: ChatsInteractor,
    private val navigation: MainNavigation
) : Presenter<ChatsView>(view) {

    override fun onViewCreated() {
        launchWithHandler {
            chatsInteractor.observeEvents()
                .collect { event ->
                    if (event is OnConnectionOpened<*>) {
                        chatsInteractor.subscribe(ChatsAction.GET)
                        chatsInteractor.observeChats()
                            .collect { chats ->
                                view.setChats(chats)
                            }
                    }
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    fun createChat() {
        navigation.chatsToCreateChat()
    }
}