package com.jnfran92.kotcoin

import android.app.Application
import com.jnfran92.kotcoin.di.component.ApplicationComponent
import com.jnfran92.kotcoin.di.component.DaggerApplicationComponent
import com.jnfran92.kotcoin.di.module.ApplicationModule
import timber.log.Timber

class KotcoinApp: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.d("onCreate")

        this.initApplicationComponent()
    }

    private fun initApplicationComponent(){
        this.applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}