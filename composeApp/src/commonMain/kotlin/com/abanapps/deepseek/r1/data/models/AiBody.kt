package com.abanapps.deepseek.r1.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AiBody(
    @SerialName("messages")
    val messages: List<MessageX>?,
    @SerialName("model")
    val model: String?,
    @SerialName("stream")
    val stream: Boolean?
)