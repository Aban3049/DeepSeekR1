package com.abanapps.deepseek.r1.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AiResponse(
    @SerialName("created_at")
    val createdAt: String?,
    @SerialName("done")
    val done: Boolean?,
    @SerialName("done_reason")
    val doneReason: String?,
    @SerialName("eval_count")
    val evalCount: Int?,
    @SerialName("eval_duration")
    val evalDuration: Double?,
    @SerialName("load_duration")
    val loadDuration: Int?,
    @SerialName("message")
    val message: Message?,
    @SerialName("model")
    val model: String?,
    @SerialName("prompt_eval_count")
    val promptEvalCount: Int?,
    @SerialName("prompt_eval_duration")
    val promptEvalDuration: Long?,
    @SerialName("total_duration")
    val totalDuration: Long?
)