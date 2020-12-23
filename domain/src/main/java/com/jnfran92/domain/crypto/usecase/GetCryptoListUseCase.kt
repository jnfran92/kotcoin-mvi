package com.jnfran92.domain.crypto.usecase

import com.jnfran92.domain.crypto.CryptoRepository
import com.jnfran92.domain.crypto.model.DomainCrypto
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.lang.Exception
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Get data
 */
class GetCryptoListUseCase @Inject constructor (
    private val repository: CryptoRepository) {

    private fun buildUseCase(): Single<List<DomainCrypto>>{
        return this.repository.getCryptoList()
//            .delay(3000, TimeUnit.MILLISECONDS)
    }

    fun toSingle(): Single<List<DomainCrypto>>{
        return buildUseCase()
    }
}