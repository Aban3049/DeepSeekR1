package com.abanapps.deepseek.r1.domain.repo

import com.abanapps.deepseek.r1.data.models.AiResponse
import com.abanapps.deepseek.r1.data.network.safeCallUtils.DataError
import com.abanapps.deepseek.r1.data.network.safeCallUtils.Result

interface Repo {

    suspend fun generateAiResponse(
        prompt: String,
        model: String
    ): Result<AiResponse, DataError.Remote>

}