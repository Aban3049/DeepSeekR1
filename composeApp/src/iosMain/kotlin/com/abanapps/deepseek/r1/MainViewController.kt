package com.abanapps.deepseek.r1

import androidx.compose.ui.window.ComposeUIViewController
import com.abanapps.deepseek.r1.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}