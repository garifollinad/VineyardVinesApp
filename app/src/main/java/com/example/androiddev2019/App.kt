package com.example.androiddev2019

import android.app.Application
import com.example.androiddev2019.core.di.appModules
import org.koin.android.ext.android.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModules)
    }
}