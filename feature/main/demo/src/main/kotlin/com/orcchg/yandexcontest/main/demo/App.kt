package com.orcchg.yandexcontest.main.demo

import android.app.Application
import timber.log.Timber

internal class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
