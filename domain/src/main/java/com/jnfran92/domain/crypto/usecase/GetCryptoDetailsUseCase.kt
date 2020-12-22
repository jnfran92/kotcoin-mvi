package com.jnfran92.domain.crypto.usecase

import com.jnfran92.domain.crypto.CryptoRepository
import com.jnfran92.domain.crypto.model.DomainCrypto
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Get data
 */
@ActivityRetainedScoped
class GetCryptoDetailsUseCase @Inject constructor(private val repository: CryptoRepository,
                              private val subscribeOnThread: Scheduler,
                              private val observeOnThread: Scheduler) {

    private fun buildUseCase(itemId: Int): Single<DomainCrypto>{
        Timber.d("buildUseCase: $itemId")
        return Single.just(DomainCrypto(-1,"Luka", "LK", "luka", 1.1, 5123123.21, "today bitches!"))
            .subscribeOn(subscribeOnThread)
            .observeOn(observeOnThread)
            .delay(3000, TimeUnit.MILLISECONDS)
    }

    fun toSingle(itemId: Int): Single<DomainCrypto>{
        return buildUseCase(itemId)
    }
}