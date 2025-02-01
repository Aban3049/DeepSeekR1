package com.abanapps.deepseek.r1

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.abanapps.deepseek.r1.di.initKoin

fun main() {
    application {

        initKoin()

        Window(
            onCloseRequest = ::exitApplication,
            title = "DeepSeekR1",
        ) {
            App()
        }
    }
}