package com.jnfran92.kotcoin.di.component

import com.jnfran92.kotcoin.di.module.ApplicationModule
import com.jnfran92.model.CryptoModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun cryptoModel(): CryptoModel
}