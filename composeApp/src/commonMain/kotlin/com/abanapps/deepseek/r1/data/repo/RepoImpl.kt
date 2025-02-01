package com.abanapps.deepseek.r1.data.repo

import com.abanapps.deepseek.r1.data.models.AiBody
import com.abanapps.deepseek.r1.data.models.AiResponse
import com.abanapps.deepseek.r1.data.models.MessageX
import com.abanapps.deepseek.r1.data.network.safeCall.safeCall
import com.abanapps.deepseek.r1.data.network.safeCallUtils.DataError
import com.abanapps.deepseek.r1.data.network.safeCallUtils.Result
import com.abanapps.deepseek.r1.domain.repo.Repo
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class RepoImpl(private val httpClient: HttpClient) : Repo {

    override suspend fun generateAiResponse(
        prompt: String,
        model: String
    ): Result<AiResponse, DataError.Remote> {

        return safeCall {
            httpClient.post("http://localhost:11434/api/chat") {
                setBody(
                    AiBody(
                        messages = listOf(MessageX(content = prompt, role = "user")),
                        model = "deepseek-coder:1.3b",
                        stream = false
                    )
                )
            }
        }

    }


}