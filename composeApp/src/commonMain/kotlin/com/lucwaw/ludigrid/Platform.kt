package com.lucwaw.ludigrid

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform