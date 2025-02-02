package com.abanapps.deepseek.r1.data.local.room.databaseFactory

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.abanapps.deepseek.r1.data.local.room.database.ChatsDatabase

actual class DatabaseFactory(
    private val context:Context
) {
    actual fun create(): RoomDatabase.Builder<ChatsDatabase> {

        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(ChatsDatabase.DB_NAME)

        return Room.databaseBuilder(context = context, name = dbFile.absolutePath)

    }

}