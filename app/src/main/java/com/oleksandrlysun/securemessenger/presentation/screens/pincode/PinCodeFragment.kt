package com.oleksandrlysun.securemessenger.presentation.screens.pincode

import android.view.View
import androidx.core.view.isVisible
import com.oleksandrlysun.securemessenger.R
import com.oleksandrlysun.securemessenger.presentation.base.PresenterFragment
import com.oleksandrlysun.securemessenger.presentation.screens.pincode.PinCodeMode.*
import kotlinx.android.synthetic.main.fragment_pin_code.*

class PinCodeFragment : PresenterFragment(), PinCodeView {

    override val layoutResId = R.layout.fragment_pin_code

    override val presenter by inject<PinCodePresenter>()

    override fun setupUI(view: View) {
        super.setupUI(view)
        oneButton.setOnClickListener(::onPinButtonClick)
        twoButton.setOnClickListener(::onPinButtonClick)
        threeButton.setOnClickListener(::onPinButtonClick)
        fourButton.setOnClickListener(::onPinButtonClick)
        fifeButton.setOnClickListener(::onPinButtonClick)
        sixButton.setOnClickListener(::onPinButtonClick)
        sevenButton.setOnClickListener(::onPinButtonClick)
        eightButton.setOnClickListener(::onPinButtonClick)
        nineButton.setOnClickListener(::onPinButtonClick)
        zeroButton.setOnClickListener(::onPinButtonClick)
        deleteButton.setOnClickListener { presenter.deletePinCode() }
    }

    override fun setMode(mode: PinCodeMode) {
        val hintResId = when (mode) {
            CREATE -> R.string.pin_code_create_hint
            CONFIRM -> R.string.pin_code_confirm_hint
            ENTER -> R.string.pin_code_enter_hint
        }
        modeTextView.setText(hintResId)
    }

    override fun setLoading(loading: Boolean) {
        loadingView.isVisible = loading
    }

    override fun setPinCode(pinCode: String) {
        pinCodeTextView.text = pinCode
    }

    private fun onPinButtonClick(view: View) {
        val digit = view.tag as String
        presenter.onPinCodeChanged(digit)
    }
}