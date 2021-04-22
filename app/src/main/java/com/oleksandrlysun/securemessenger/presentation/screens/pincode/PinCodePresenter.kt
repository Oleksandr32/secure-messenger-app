package com.oleksandrlysun.securemessenger.presentation.screens.pincode

import com.oleksandrlysun.securemessenger.extensions.uiThread
import com.oleksandrlysun.securemessenger.interactors.auth.AuthInteractor
import com.oleksandrlysun.securemessenger.presentation.base.Arguments
import com.oleksandrlysun.securemessenger.presentation.base.Presenter
import com.oleksandrlysun.securemessenger.presentation.navigation.MainNavigation
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class PinCodePresenter(
    view: PinCodeView,
    private val navigation: MainNavigation,
    private val authInteractor: AuthInteractor
) : Presenter<PinCodeView>(view) {

    companion object {
        private const val KEY_MODE = "mode"
        private const val PIN_CODE_CHAR = "*"
        private const val PIN_CODE_LENGTH = 4
    }

    private val disposable = CompositeDisposable()

    private lateinit var mode: PinCodeMode
    private var pinCode = ""
    private var confirmPinCode = ""

    override fun onViewCreated(arguments: Arguments) {
        mode = arguments.getSerializable(KEY_MODE) as PinCodeMode
        view.setMode(mode)
    }

    override fun onDestroyView() {
        disposable.dispose()
    }

    fun onPinCodeChanged(digit: String) {
        pinCode += digit
        if (pinCode.length == PIN_CODE_LENGTH) {
            onPinCodeEntered()
        }
        updatePinCode()
    }

    fun deletePinCode() {
        pinCode = pinCode.dropLast(1)
        updatePinCode()
    }

    private fun onPinCodeEntered() {
        when (mode) {
            PinCodeMode.CREATE -> {
                confirmPinCode = pinCode
                pinCode = ""
                mode = PinCodeMode.CONFIRM
                view.setMode(mode)
            }
            PinCodeMode.CONFIRM -> {
                if (pinCode == confirmPinCode) {
                    authInteractor.savePinCode(pinCode)
                    navigation.pinCodeToChats()
                } else {
                    pinCode = ""
                    confirmPinCode = ""
                    mode = PinCodeMode.CREATE
                    view.setMode(mode)
                }
            }
            PinCodeMode.ENTER -> {
                val isPinCodeValid = authInteractor.checkPinCode(pinCode)
                if (isPinCodeValid) authorize() else pinCode = ""
            }
        }
    }

    private fun updatePinCode() {
        view.setPinCode(PIN_CODE_CHAR.repeat(pinCode.length))
    }

    private fun authorize() {
        view.setLoading(true)
        listenUser()
        authInteractor.login()
    }

    private fun listenUser() {
        authInteractor.observeUser()
            .uiThread()
            .subscribe { navigation.pinCodeToChats() }
            .addTo(disposable)
    }
}