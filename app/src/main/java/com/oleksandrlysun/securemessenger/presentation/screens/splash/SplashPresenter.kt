package com.oleksandrlysun.securemessenger.presentation.screens.splash

import com.oleksandrlysun.securemessenger.extensions.uiThread
import com.oleksandrlysun.securemessenger.interactors.auth.AuthInteractor
import com.oleksandrlysun.securemessenger.presentation.base.Arguments
import com.oleksandrlysun.securemessenger.presentation.base.Presenter
import com.oleksandrlysun.securemessenger.presentation.navigation.MainNavigation
import com.tinder.scarlet.WebSocket.Event.OnConnectionOpened
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit

class SplashPresenter(
    view: SplashView,
    private val navigation: MainNavigation,
    private val authInteractor: AuthInteractor
) : Presenter<SplashView>(view) {

    companion object {
        private const val START_DELAY = 500L
    }

    private val disposable = CompositeDisposable()

    override fun onViewCreated(arguments: Arguments) {
        authInteractor.observeEvents()
            .filter { it is OnConnectionOpened<*> }
            .delay(START_DELAY, TimeUnit.MILLISECONDS)
            .uiThread()
            .subscribe { init() }
            .addTo(disposable)
    }

    override fun onDestroyView() {
        disposable.dispose()
    }

    private fun init() {
        if (authInteractor.isAuthorized()) {
            navigation.splashToPinCode()
        } else {
            navigation.splashToLogin()
        }
    }
}