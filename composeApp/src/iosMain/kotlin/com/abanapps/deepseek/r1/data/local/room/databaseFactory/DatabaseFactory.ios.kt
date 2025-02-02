package com.abanapps.deepseek.r1.data.local.room.databaseFactory

import androidx.room.Room
import androidx.room.RoomDatabase
import com.abanapps.deepseek.r1.data.local.room.database.ChatsDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual class DatabaseFactory {

    actual fun create(): RoomDatabase.Builder<ChatsDatabase> {
        val dbFile = documentDirectory() + "/${ChatsDatabase.DB_NAME}"
        return Room.databaseBuilder<ChatsDatabase>(
            name = dbFile
        )
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun documentDirectory(): String {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null
        )

        return requireNotNull(documentDirectory?.path)
    }

}

