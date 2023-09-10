package com.kmmloginscreen.domain.base

interface ErrorLogger {
    fun log(throwable: Throwable, message: String)
    fun log(throwable: Throwable)
    fun log(message: String)
}
