package org.kenramandha.crypto_apps

import androidx.compose.ui.window.ComposeUIViewController
import org.kenramandha.crypto_apps.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }