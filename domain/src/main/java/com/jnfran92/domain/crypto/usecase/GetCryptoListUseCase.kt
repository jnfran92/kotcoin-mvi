package com.jnfran92.domain.crypto.usecase

import com.jnfran92.domain.crypto.CryptoRepository
import com.jnfran92.domain.crypto.model.DomainCrypto
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
class GetCryptoListUseCase(private val repository: CryptoRepository,
                                       private val subscribeOnThread: Scheduler,
                                       private val observeOnThread: Scheduler) {


    val useCase =  buildUseCase()
//    private val compositeDisposable = CompositeDisposable()

    private fun buildUseCase(): Single<List<DomainCrypto>>{
        return this.repository.getCryptoList()
            .subscribeOn(subscribeOnThread)
            .observeOn(observeOnThread)
            .delay(3000, TimeUnit.MILLISECONDS)
    }

//    fun execute(disposableSingleObserver: DisposableSingleObserver<List<DomainCrypto>>){
//        Timber.d("execute: ")
//        compositeDisposable += buildUseCase().subscribeWith(disposableSingleObserver)
//    }

//    fun dispose(){
//        this.compositeDisposable.dispose()
//    }
}