package com.abanapps.deepseek.r1.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageX(
    @SerialName("content")
    val content: String?,
    @SerialName("role")
    val role: String?
)