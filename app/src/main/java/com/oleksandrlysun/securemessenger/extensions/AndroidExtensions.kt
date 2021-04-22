package com.oleksandrlysun.securemessenger.extensions

import androidx.fragment.app.Fragment
import com.oleksandrlysun.securemessenger.presentation.screens.MainActivity

val Fragment.mainActivity: MainActivity?
    get() = activity as? MainActivity