package com.oleksandrlysun.securemessenger.di

import com.oleksandrlysun.securemessenger.api.ApiService
import com.oleksandrlysun.securemessenger.api.ScarletService
import com.oleksandrlysun.securemessenger.api.impl.ApiServiceImpl
import com.oleksandrlysun.securemessenger.api.utils.FlowStreamAdapter
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.lifecycle.android.AndroidLifecycle
import com.tinder.scarlet.messageadapter.gson.GsonMessageAdapter
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.koin.dsl.module

private const val API_URL = ""

val apiModule = module {

    single { HttpLoggingInterceptor().setLevel(Level.BODY) }

    single {
        OkHttpClient.Builder()
            .protocols(listOf(Protocol.HTTP_1_1))
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        Scarlet.Builder()
            .webSocketFactory(get<OkHttpClient>().newWebSocketFactory(API_URL))
            .addMessageAdapterFactory(GsonMessageAdapter.Factory())
            .addStreamAdapterFactory(FlowStreamAdapter.Factory)
            .lifecycle(AndroidLifecycle.ofApplicationForeground(get()))
            .build()
            .create<ScarletService>()
    }


    single<ApiService> { ApiServiceImpl(get()) }
}