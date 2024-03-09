package com.kmmloginscreen.android.presentation.base

import com.kmmloginscreen.domain.base.ErrorLogger
import timber.log.Timber

class DebugErrorLogger : ErrorLogger {
    override fun log(throwable: Throwable, message: String) = Timber.e(throwable, message)
    override fun log(throwable: Throwable) = Timber.e(throwable)
    override fun log(message: String) = Timber.e(message)
}
