package com.abanapps.deepseek.r1

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "DeepSeekR1",
    ) {
        App()
    }
}