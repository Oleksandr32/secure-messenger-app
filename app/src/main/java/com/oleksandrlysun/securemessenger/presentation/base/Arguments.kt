package com.oleksandrlysun.securemessenger.presentation.base

import android.os.Bundle

class Arguments(private val bundle: Bundle?) {

    fun getSerializable(key: String) = bundle?.getSerializable(key)
}