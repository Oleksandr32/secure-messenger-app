package com.oleksandrlysun.securemessenger.presentation.screens.login

import com.oleksandrlysun.securemessenger.extensions.uiThread
import com.oleksandrlysun.securemessenger.interactors.auth.AuthInteractor
import com.oleksandrlysun.securemessenger.presentation.base.Arguments
import com.oleksandrlysun.securemessenger.presentation.base.Presenter
import com.oleksandrlysun.securemessenger.presentation.navigation.MainNavigation
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class LoginPresenter(
    view: LoginView,
    private val navigation: MainNavigation,
    private val authInteractor: AuthInteractor
) : Presenter<LoginView>(view) {

    private val disposable = CompositeDisposable()

    override fun onViewCreated(arguments: Arguments) {
        authInteractor.observeUser()
            .uiThread()
            .subscribe { navigation.loginToPinCode() }
            .addTo(disposable)
    }

    override fun onDestroyView() {
        disposable.dispose()
    }

    fun onRegisterClick() {
        navigation.loginToRegister()
    }

    fun login(username: String?, password: String?) {
        if (username.isNullOrBlank() || password.isNullOrBlank()) return
        view.setLoading(true)
        authInteractor.login(username, password)
    }
}