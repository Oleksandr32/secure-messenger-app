package com.oleksandrlysun.securemessenger.di

import com.oleksandrlysun.securemessenger.preferences.*
import com.oleksandrlysun.securemessenger.preferences.impl.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val preferencesModule = module {

    single<Preferences> { PreferencesImpl(androidContext()) }

    single { get<Preferences>().userPreferences }
}