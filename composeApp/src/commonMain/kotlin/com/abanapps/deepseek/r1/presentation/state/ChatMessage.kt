package com.abanapps.deepseek.r1.presentation.state

data class ChatMessage(
    val userPrompt: String,
    val aiResponse: String? = null,
    val isLoading: Boolean = false
)
