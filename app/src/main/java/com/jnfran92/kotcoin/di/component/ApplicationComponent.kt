package com.jnfran92.kotcoin.di.component

import android.content.Context
import com.jnfran92.kotcoin.di.module.ApplicationModule
import com.jnfran92.kotcoin.rx.ObserverThread
import com.jnfran92.kotcoin.rx.SubscriberExecutor
import com.jnfran92.model.CryptoModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    // exposed to sub-graphs
    fun cryptoModel(): CryptoModel
    fun context(): Context
    fun observerThread(): ObserverThread
    fun subscriberThread(): SubscriberExecutor
}