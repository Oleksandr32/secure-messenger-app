package com.oleksandrlysun.securemessenger.presentation.screens

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.oleksandrlysun.securemessenger.R
import com.oleksandrlysun.securemessenger.extensions.hideSoftInput
import com.oleksandrlysun.securemessenger.presentation.navigation.MainNavigation
import org.koin.android.scope.ScopeActivity

class MainActivity : ScopeActivity(), NavController.OnDestinationChangedListener {

    private val navigation by inject<MainNavigation>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        navigation.subscribe(this)
    }

    override fun onStop() {
        super.onStop()
        navigation.unsubscribe(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigation.navigateUp()
    }

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        currentFocus?.hideSoftInput()
    }
}
