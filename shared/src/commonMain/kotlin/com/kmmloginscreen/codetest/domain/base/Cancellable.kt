package com.kmmloginscreen.domain.base

interface Cancellable {
    fun cancel()
}

interface RunningExecution : Cancellable {
    fun isRunning(): Boolean
}

fun MutableSet<Cancellable>.cancelAll() {
    forEach { it.cancel() }
    clear()
}
