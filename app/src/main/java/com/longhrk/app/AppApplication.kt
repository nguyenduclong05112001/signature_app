package com.longhrk.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.Forest.plant


@HiltAndroidApp
class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        plant(Timber.DebugTree())
    }
}