package com.oleksandrlysun.securemessenger.presentation.screens.chat

import android.view.View
import com.oleksandrlysun.securemessenger.R
import com.oleksandrlysun.securemessenger.extensions.value
import com.oleksandrlysun.securemessenger.models.Chat
import com.oleksandrlysun.securemessenger.models.Message
import com.oleksandrlysun.securemessenger.presentation.base.PresenterFragment
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : PresenterFragment(), ChatView {

    override val layoutResId = R.layout.fragment_chat

    override val presenter by inject<ChatPresenter>()

    private val messagesAdapter = MessagesAdapter()

    override fun setupUI(view: View) {
        super.setupUI(view)
        displayHomeAsUp()
        messagesRecyclerView.adapter = messagesAdapter
        //messagesRecyclerView.addItemDecoration()
        sendButton.setOnClickListener { onSendClick() }
    }

    override fun setChat(chat: Chat) {
        toolbar.title = chat.other.fullName
       // chatViewFlipper.displayedChild = chat.status.ordinal
    }

    override fun setMessages(messages: List<Message>) {
        messagesAdapter.setItems(messages)
    }

    override fun setNewMessage(message: Message) {
        messagesAdapter.addItem(message)
        messagesRecyclerView.smoothScrollToPosition(0)
    }

    override fun onMessageSent() {
        messageEditText.text.clear()
    }

    private fun onSendClick() {
        presenter.send(messageEditText.value)
    }
}