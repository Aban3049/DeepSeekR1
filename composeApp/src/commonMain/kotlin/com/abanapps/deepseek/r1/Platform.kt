package com.abanapps.deepseek.r1

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform