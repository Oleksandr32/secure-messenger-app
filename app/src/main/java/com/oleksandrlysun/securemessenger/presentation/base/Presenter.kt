package com.oleksandrlysun.securemessenger.presentation.base

import androidx.annotation.CallSuper
import com.oleksandrlysun.securemessenger.presentation.navigation.MainNavigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class Presenter<T : BaseView>(protected val view: T) {

    private val job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)

    open fun onViewCreated() {}

    @CallSuper
    open fun onDestroyView() {
        job.cancel()
    }

    protected fun launchWithHandler(block: suspend () -> Unit) {
        coroutineScope.launch(job) {
            try {
                block()
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }
}