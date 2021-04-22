package com.oleksandrlysun.securemessenger.presentation.base

abstract class Presenter<T : BaseView>(protected val view: T) {

    open fun onViewCreated(arguments: Arguments) {}

    open fun onViewResume() {}

    open fun onViewPause() {}

    open fun onDestroyView() {
    }
}