package com.oleksandrlysun.securemessenger.presentation.screens.register

import com.oleksandrlysun.securemessenger.extensions.uiThread
import com.oleksandrlysun.securemessenger.interactors.auth.AuthInteractor
import com.oleksandrlysun.securemessenger.models.User
import com.oleksandrlysun.securemessenger.presentation.base.Arguments
import com.oleksandrlysun.securemessenger.presentation.base.Presenter
import com.oleksandrlysun.securemessenger.presentation.navigation.MainNavigation
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class RegisterPresenter(
    view: RegisterView,
    private val navigation: MainNavigation,
    private val authInteractor: AuthInteractor
) : Presenter<RegisterView>(view) {

    private val disposable = CompositeDisposable()

    override fun onViewCreated(arguments: Arguments) {
        authInteractor.observeUser()
            .uiThread()
            .subscribe { navigation.registerToPinCode() }
            .addTo(disposable)
    }

    override fun onDestroyView() {
        disposable.dispose()
    }

    fun register(firstName: String?, lastName: String?, username: String?, password: String?) {
        if (firstName.isNullOrBlank() || lastName.isNullOrBlank() || username.isNullOrBlank() || password.isNullOrBlank()) return
        view.setLoading(true)
        val user = User(
            firstName = firstName,
            lastName = lastName,
            userName = username,
            password = password
        )
        authInteractor.register(user)
    }
}