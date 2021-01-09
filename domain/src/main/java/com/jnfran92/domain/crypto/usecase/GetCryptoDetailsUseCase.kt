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
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Get data
 */
class GetCryptoDetailsUseCase @Inject constructor(
    private val repository: CryptoRepository) {

    private fun buildUseCase(itemId: Long): Single<DomainCrypto>{
        Timber.d("buildUseCase: $itemId")
        return Single.just(DomainCrypto(-1,"Luka", "LK", "luka", 1.1, 5123123.21, "today bitches!"))
    }

    fun toSingle(itemId: Long): Single<DomainCrypto>{
        return buildUseCase(itemId)
    }
}