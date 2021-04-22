package com.oleksandrlysun.securemessenger.presentation.screens.splash

import com.oleksandrlysun.securemessenger.R
import com.oleksandrlysun.securemessenger.presentation.base.PresenterFragment

class SplashFragment : PresenterFragment(), SplashView {

    override val layoutResId = R.layout.fragment_splash

    override val presenter by inject<SplashPresenter>()
}