package com.oleksandrlysun.securemessenger.presentation.screens.pincode

import com.oleksandrlysun.securemessenger.presentation.base.BaseView

interface PinCodeView : BaseView {

    fun setMode(mode: PinCodeMode)

    fun setLoading(loading: Boolean)

    fun setPinCode(pinCode: String)
}