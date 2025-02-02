package com.abanapps.deepseek.r1.data.repo

import androidx.sqlite.SQLiteException
import com.abanapps.deepseek.r1.data.local.room.dao.ChatDao
import com.abanapps.deepseek.r1.data.local.room.entity.ChatEntity
import com.abanapps.deepseek.r1.data.models.AiBody
import com.abanapps.deepseek.r1.data.models.AiResponse
import com.abanapps.deepseek.r1.data.models.MessageX
import com.abanapps.deepseek.r1.data.network.safeCall.safeCall
import com.abanapps.deepseek.r1.data.network.safeCallUtils.DataError
import com.abanapps.deepseek.r1.data.network.safeCallUtils.EmptyResult
import com.abanapps.deepseek.r1.data.network.safeCallUtils.Result
import com.abanapps.deepseek.r1.domain.repo.Repo
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.Flow

class RepoImpl(private val httpClient: HttpClient, private val chatsDao: ChatDao) : Repo {

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

    suspend fun addChat(chatEntity: ChatEntity): EmptyResult<DataError.Local> {
        return try {
            chatsDao.upsert(chatEntity)
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

     fun getChats():Flow<List<ChatEntity>>{
        return chatsDao.getAllChats()
    }

   suspend fun deleteChat(id:Long){
        chatsDao.deleteChat(id)
    }


}