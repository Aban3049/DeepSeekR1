package com.abanapps.deepseek.r1.data.local.room.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abanapps.deepseek.r1.data.local.room.dao.ChatDao
import com.abanapps.deepseek.r1.data.local.room.databaseConstructor.ChatDatabaseConstructor
import com.abanapps.deepseek.r1.data.local.room.entity.ChatEntity
import com.abanapps.deepseek.r1.data.local.room.typeConvertor.ChatTypeConvertor

@Database(entities = [ChatEntity::class], version = 1)
@TypeConverters(ChatTypeConvertor::class)
@ConstructedBy(ChatDatabaseConstructor::class)
abstract class ChatsDatabase : RoomDatabase() {


    abstract val chatsDao:ChatDao

    companion object{
        const val DB_NAME = "chats.db"
    }


}