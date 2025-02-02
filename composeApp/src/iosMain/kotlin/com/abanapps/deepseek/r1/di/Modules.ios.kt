package com.abanapps.deepseek.r1.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.abanapps.deepseek.r1.data.local.room.databaseFactory.DatabaseFactory
import com.abanapps.deepseek.r1.data.utils.Utils
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import kotlinx.cinterop.ExperimentalForeignApi
import okio.Path.Companion.toPath
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
val directory = NSFileManager.defaultManager.URLForDirectory(
    directory = NSDocumentDirectory,
    inDomain = NSUserDomainMask,
    appropriateForURL = null,
    create = false,
    error = null
)

actual val platformModule: Module
    get() = module {

        single<HttpClientEngine> {
            Darwin.create()
        }

        single<DataStore<Preferences>> {

            PreferenceDataStoreFactory.createWithPath(
                produceFile = {
                    "${requireNotNull(directory).path}/${Utils.DATA_STORE_FILE_NAME}".toPath()
                }
            )
        }

        single {
            DatabaseFactory()
        }

    }