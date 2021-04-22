package com.oleksandrlysun.securemessenger.presentation.screens.register

import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isInvisible
import com.oleksandrlysun.securemessenger.R
import com.oleksandrlysun.securemessenger.extensions.hideSoftInput
import com.oleksandrlysun.securemessenger.extensions.value
import com.oleksandrlysun.securemessenger.presentation.base.PresenterFragment
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : PresenterFragment(), RegisterView {

    override val layoutResId = R.layout.fragment_register

    override val presenter by inject<RegisterPresenter>()

    override fun setupUI(view: View) {
        super.setupUI(view)
        setupPasswordInput()
        loginButton.setOnClickListener { onRegister() }
    }

    override fun setLoading(loading: Boolean) {
        loadingView.isInvisible = !loading
    }

    private fun setupPasswordInput() {
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onRegister()
                passwordEditText.hideSoftInput()
                passwordEditText.clearFocus()
            }

            return@setOnEditorActionListener false
        }
    }

    private fun onRegister() {
        presenter.register(
            firstNameEditText.value,
            lastNameEditText.value,
            userNameEditText.value,
            passwordEditText.value
        )
    }

}