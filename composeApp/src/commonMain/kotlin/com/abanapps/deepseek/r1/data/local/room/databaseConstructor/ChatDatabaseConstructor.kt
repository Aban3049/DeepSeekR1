package com.abanapps.deepseek.r1.data.local.room.databaseConstructor

import androidx.room.RoomDatabaseConstructor
import com.abanapps.deepseek.r1.data.local.room.database.ChatsDatabase

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object ChatDatabaseConstructor:RoomDatabaseConstructor<ChatsDatabase>{

    override fun initialize(): ChatsDatabase

}