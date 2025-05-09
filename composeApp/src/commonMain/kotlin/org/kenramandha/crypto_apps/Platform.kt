package org.kenramandha.crypto_apps

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform