package com.abanapps.deepseek.r1.data.local.room.databaseFactory

import androidx.room.Room
import androidx.room.RoomDatabase
import com.abanapps.deepseek.r1.data.local.room.database.ChatsDatabase
import java.io.File

actual class DatabaseFactory {

    actual fun create(): RoomDatabase.Builder<ChatsDatabase> {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "DeepSeekR1")
            os.contains("mac") -> File(userHome, "Library/Application Support/DeepSeekR1")
            else -> File(userHome, ".local/share/DeepSeekR1")
        }

        if(!appDataDir.exists()) {
            appDataDir.mkdirs()
        }

        val dbFile = File(appDataDir, ChatsDatabase.DB_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)
    }
}