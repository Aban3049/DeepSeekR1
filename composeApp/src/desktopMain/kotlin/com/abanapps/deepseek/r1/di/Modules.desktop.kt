package com.abanapps.deepseek.r1.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.abanapps.deepseek.r1.data.local.room.databaseFactory.DatabaseFactory
import com.abanapps.deepseek.r1.data.utils.Utils
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import okio.Path.Companion.toPath
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {

        single<HttpClientEngine> {
            OkHttp.create()
        }

        single { DatabaseFactory() }

        single<DataStore<Preferences>> {
            PreferenceDataStoreFactory.createWithPath(
                produceFile = {
                    Utils.DATA_STORE_FILE_NAME.toPath()
                }
            )
        }

    }