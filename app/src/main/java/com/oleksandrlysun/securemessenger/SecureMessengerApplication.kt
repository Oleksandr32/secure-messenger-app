package com.oleksandrlysun.securemessenger

import android.app.Application
import com.oleksandrlysun.securemessenger.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SecureMessengerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SecureMessengerApplication)
            modules(appModule)
        }
    }
}