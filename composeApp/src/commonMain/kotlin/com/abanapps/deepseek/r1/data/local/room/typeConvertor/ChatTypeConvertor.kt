package com.abanapps.deepseek.r1.data.local.room.typeConvertor

import androidx.room.TypeConverter
import com.abanapps.deepseek.r1.presentation.state.ChatMessage
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object ChatTypeConvertor {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    @TypeConverter
    fun fromChat(chatMessage: ChatMessage): String {
        return json.encodeToString(chatMessage)
    }

    @TypeConverter
    fun toChat(chatMessage: String): ChatMessage {
        return json.decodeFromString(chatMessage)
    }


}