package com.abanapps.deepseek.r1.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.abanapps.deepseek.r1.data.local.room.entity.ChatEntity
import com.abanapps.deepseek.r1.presentation.state.ChatMessage
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {

    @Upsert
    suspend fun upsert(chat: ChatEntity)

    @Query("SELECT * FROM ChatEntity")
    fun getAllChats(): Flow<List<ChatEntity>>

    @Query("DELETE FROM ChatEntity WHERE id = :id")
    suspend fun deleteChat(id: Long)


}