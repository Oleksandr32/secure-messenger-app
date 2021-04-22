package com.oleksandrlysun.securemessenger.presentation.screens.chat

import com.oleksandrlysun.securemessenger.extensions.uiThread
import com.oleksandrlysun.securemessenger.interactors.chat.ChatAction
import com.oleksandrlysun.securemessenger.interactors.chat.ChatInteractor
import com.oleksandrlysun.securemessenger.models.Chat
import com.oleksandrlysun.securemessenger.models.Message
import com.oleksandrlysun.securemessenger.presentation.base.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import java.util.*

class ChatPresenter(
    view: ChatView,
    private val chatInteractor: ChatInteractor
) : Presenter<ChatView>(view) {

    companion object {
        private const val KEY_CHAT = "chat"
    }

    private val compositeDisposable = CompositeDisposable()

    private lateinit var chat: Chat
    private val messages = LinkedList<Message>()

    override fun onViewCreated(arguments: Arguments) {
        chat = arguments.getSerializable(KEY_CHAT) as Chat
        chatInteractor.openChat(chat)
        view.setChat(chat)

        listenChat()
        listenMessages()
        listenNewMessage()
        chatInteractor.subscribe(ChatAction.GET_MESSAGES, chat.id)
    }

    override fun onDestroyView() {
        compositeDisposable.dispose()
    }

    fun send(message: String) {
        if (message.isNotBlank()) {
            chatInteractor.sendMessage(chat, message)
            view.onMessageSent()
        }
    }

    private fun listenChat() {
        chatInteractor.observeChat()
            .filter { it.id == chat.id }
            .filter { it.me != null}
            .uiThread()
            .subscribe {
                chat = it
                view.setChat(it)
            }
            .addTo(compositeDisposable)

    }

    private fun listenMessages() {
        chatInteractor.observeMessages()
            .uiThread()
            .subscribe {
                messages.addAll(it)
                view.setMessages(it)
            }
            .addTo(compositeDisposable)
    }

    private fun listenNewMessage() {
        chatInteractor.observeMessage()
            .filter { it.body != null }
            .uiThread()
            .subscribe { message ->
                messages.addFirst(message)
                view.setNewMessage(message)
            }
            .addTo(compositeDisposable)
    }
}