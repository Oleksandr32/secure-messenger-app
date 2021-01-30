package com.oleksandrlysun.securemessenger.presentation.screens.chats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oleksandrlysun.securemessenger.R
import com.oleksandrlysun.securemessenger.presentation.utils.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_chats.*

class ChatsFragment : Fragment() {

    private val chatsAdapter = ChatsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_chats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chatsRecyclerView.adapter = chatsAdapter
        chatsRecyclerView.addItemDecoration(DividerItemDecoration(view.context))
    }
}