package com.oleksandrlysun.securemessenger.presentation.screens.createchat

import android.view.View
import com.oleksandrlysun.securemessenger.R
import com.oleksandrlysun.securemessenger.models.User
import com.oleksandrlysun.securemessenger.presentation.base.PresenterFragment
import com.oleksandrlysun.securemessenger.extensions.setOnTextChangeListener
import com.oleksandrlysun.securemessenger.extensions.showSoftInput
import com.oleksandrlysun.securemessenger.presentation.views.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_create_chat.*
import org.koin.core.parameter.parametersOf

class CreateChatFragment : PresenterFragment(), CreateChatView {

    override val layoutResId = R.layout.fragment_create_chat

    override val presenter by inject<CreateChatPresenter> { parametersOf(this) }

    private val usersAdapter = UsersAdapter()

    override fun setupUI(view: View) {
        super.setupUI(view)
        displayHomeAsUp()

        usersRecyclerView.adapter = usersAdapter.apply { listener = presenter::createChat }
        usersRecyclerView.addItemDecoration(DividerItemDecoration(view.context))

        searchEditText.setOnTextChangeListener { presenter.search(it) }
        searchEditText.showSoftInput()
    }

    override fun setUsers(users: List<User>) {
        usersAdapter.items = users
    }
}