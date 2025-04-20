package com.raya_challenge

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform