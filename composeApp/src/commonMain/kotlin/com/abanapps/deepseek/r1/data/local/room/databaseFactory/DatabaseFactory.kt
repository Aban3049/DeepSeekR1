package com.abanapps.deepseek.r1.data.local.room.databaseFactory

import androidx.room.RoomDatabase
import com.abanapps.deepseek.r1.data.local.room.database.ChatsDatabase

expect class DatabaseFactory {

    fun create(): RoomDatabase.Builder<ChatsDatabase>

}
