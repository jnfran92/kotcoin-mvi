package com.jnfran92.kotcoin

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class KotcoinApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.d("onCreate")
    }

    override fun getWorkManagerConfiguration(): Configuration {
        Timber.d("getWorkManagerConfiguration")
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
}