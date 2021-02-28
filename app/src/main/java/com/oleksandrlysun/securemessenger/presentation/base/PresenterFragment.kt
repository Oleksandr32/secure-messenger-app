package com.oleksandrlysun.securemessenger.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import com.oleksandrlysun.securemessenger.R
import com.oleksandrlysun.securemessenger.presentation.extensions.mainActivity
import org.koin.android.scope.ScopeFragment

abstract class PresenterFragment : ScopeFragment(), BaseView {

    protected abstract val layoutResId: Int

    protected abstract val presenter: Presenter<out BaseView>

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResId, container, false)
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI(view)
        presenter.onViewCreated()
    }

    override fun onDestroyView() {
        presenter.onDestroyView()
        super.onDestroyView()
    }

    @CallSuper
    protected open fun setupUI(view: View) {
        setupToolbar(view)
    }

    @CallSuper
    protected open fun setupToolbar(view: View) {
        mainActivity?.setSupportActionBar(view.findViewById(R.id.toolbar))
    }

    protected fun displayHomeAsUp() {
        mainActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}