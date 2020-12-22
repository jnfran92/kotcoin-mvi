package com.jnfran92.kotcoin

import android.app.Application
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class KotcoinApp: Application() {

//    /**
//     * Dagger component
//     */
//    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.d("onCreate")

//        this.initApplicationComponent()
    }

//    private fun initApplicationComponent(){
//        this.applicationComponent = DaggerApplicationComponent
//            .builder()
//            .applicationModule(ApplicationModule(this))
//            .build()
//    }
}