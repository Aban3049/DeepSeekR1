package com.abanapps.deepseek.r1.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.abanapps.deepseek.r1.data.local.room.typeConvertor.ChatTypeConvertor
import com.abanapps.deepseek.r1.presentation.state.ChatMessage

@Entity
data class ChatEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @TypeConverters(ChatTypeConvertor::class)
    val chat: ChatMessage
)