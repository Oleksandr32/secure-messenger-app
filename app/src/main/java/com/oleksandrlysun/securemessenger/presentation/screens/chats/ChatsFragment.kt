package com.oleksandrlysun.securemessenger.presentation.screens.chats

import android.view.View
import com.oleksandrlysun.securemessenger.R
import com.oleksandrlysun.securemessenger.models.Chat
import com.oleksandrlysun.securemessenger.presentation.base.PresenterFragment
import com.oleksandrlysun.securemessenger.presentation.views.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_chats.*

class ChatsFragment : PresenterFragment(), ChatsView {

    override val layoutResId = R.layout.fragment_chats

    override val presenter by inject<ChatsPresenter>()

    private val chatsAdapter = ChatsAdapter()

    override fun setupUI(view: View) {
        super.setupUI(view)
        chatsRecyclerView.adapter = chatsAdapter
        chatsRecyclerView.addItemDecoration(
            DividerItemDecoration(
                view.context
            )
        )
        createChatButton.setOnClickListener { presenter.createChat() }
    }

    override fun setChats(chats: List<Chat>) {
        chatsAdapter.items = chats
    }
}