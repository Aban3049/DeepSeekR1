package com.abanapps.deepseek.r1.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.abanapps.deepseek.r1.data.local.room.database.ChatsDatabase
import com.abanapps.deepseek.r1.data.local.room.databaseFactory.DatabaseFactory
import com.abanapps.deepseek.r1.data.network.Client.HttpClientFactory
import com.abanapps.deepseek.r1.data.repo.RepoImpl
import com.abanapps.deepseek.r1.domain.repo.Repo
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import com.abanapps.deepseek.r1.domain.viewModel.MainViewModel

expect val platformModule: Module

val sharedModule = module {

    single { HttpClientFactory.createHttpClient(get()) }
    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<ChatsDatabase>().chatsDao }
    singleOf(::RepoImpl).bind<Repo>()
    viewModelOf(::MainViewModel)

}