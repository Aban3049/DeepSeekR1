package com.abanapps.deepseek.r1.app

import android.app.Application
import com.abanapps.deepseek.r1.di.initKoin
import org.koin.android.ext.koin.androidContext

class App:Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidContext(this@App)
        }
    }

}