package com.jnfran92.kotcoin.di.component

import android.content.Context
import com.jnfran92.domain.crypto.CryptoRepository
import com.jnfran92.domain.crypto.usecase.GetCryptoListUseCase
import com.jnfran92.kotcoin.di.module.ApplicationModule
import com.jnfran92.kotcoin.presentation.crypto.CryptoListViewModel
import com.jnfran92.kotcoin.rx.ObserverThread
import com.jnfran92.kotcoin.rx.SubscriberExecutor
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    // exposed to sub-graphs
    fun CryptoRepository(): CryptoRepository
    fun context(): Context
    fun observerThread(): ObserverThread
    fun subscriberThread(): SubscriberExecutor

    fun getCryptoListUseCase(): GetCryptoListUseCase

    // view model
    fun inject(cryptoListViewModel: CryptoListViewModel)
}