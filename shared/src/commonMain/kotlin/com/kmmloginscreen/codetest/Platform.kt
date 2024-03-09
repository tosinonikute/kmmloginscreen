package com.kmmloginscreen

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform