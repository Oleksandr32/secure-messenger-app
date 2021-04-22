package com.oleksandrlysun.securemessenger.presentation.screens.login

import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isInvisible
import com.oleksandrlysun.securemessenger.R
import com.oleksandrlysun.securemessenger.extensions.hideSoftInput
import com.oleksandrlysun.securemessenger.extensions.value
import com.oleksandrlysun.securemessenger.presentation.base.PresenterFragment
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : PresenterFragment(), LoginView {

    override val layoutResId = R.layout.fragment_login

    override val presenter by inject<LoginPresenter>()

    override fun setupUI(view: View) {
        super.setupUI(view)
        setupPasswordInput()
        registerButton.setOnClickListener { presenter.onRegisterClick() }
        loginButton.setOnClickListener { presenter.login(userNameEditText.value, passwordEditText.value) }
    }

    override fun setLoading(loading: Boolean) {
        loadingView.isInvisible = !loading
    }

    private fun setupPasswordInput() {
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                presenter.login(userNameEditText.value, passwordEditText.value)
                passwordEditText.hideSoftInput()
                passwordEditText.clearFocus()
            }

            return@setOnEditorActionListener false
        }
    }
}