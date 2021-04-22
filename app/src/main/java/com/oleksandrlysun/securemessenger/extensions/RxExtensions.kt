package com.oleksandrlysun.securemessenger.extensions

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Flowable<T>.ioThread() = subscribeOn(Schedulers.io())

fun <T> Flowable<T>.uiThread() = observeOn(AndroidSchedulers.mainThread())