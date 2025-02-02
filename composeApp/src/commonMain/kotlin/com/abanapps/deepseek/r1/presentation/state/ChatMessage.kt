package com.abanapps.deepseek.r1.presentation.state

import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(
    val userPrompt: String,
    val aiResponse: String? = null,
    val currentTime: Long? = null,
    val isLoading: Boolean = false
)
