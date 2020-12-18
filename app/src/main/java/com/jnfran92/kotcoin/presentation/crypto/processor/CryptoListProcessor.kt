package com.jnfran92.kotcoin.presentation.crypto.processor

import com.jnfran92.domain.crypto.model.DomainCrypto
import com.jnfran92.domain.crypto.usecase.GetCryptoListUseCase
import com.jnfran92.kotcoin.presentation.crypto.action.CryptoListAction
import com.jnfran92.kotcoin.presentation.crypto.mapper.DomainCryptoToUIMapper
import com.jnfran92.kotcoin.presentation.crypto.result.CryptoListResult
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

class CryptoListProcessor @Inject constructor(
    private val getCryptoListUseCase: GetCryptoListUseCase,
    private val mapper: DomainCryptoToUIMapper) {

    /**
     * tx
     */
    val tx: PublishSubject<CryptoListResult> = PublishSubject.create()

    /**
     * rx
     */
    val rx = object : DisposableObserver<CryptoListAction>(){
        override fun onNext(t: CryptoListAction) {
            Timber.d("onNext")
            when(t){
                CryptoListAction.getCryptoList -> { executeGetCryptoListUseCase()}
                is CryptoListAction.getCryptoItemDetails -> {}
            }
        }

        override fun onError(e: Throwable) {
            Timber.d("onError")
        }

        override fun onComplete() {
            Timber.d("onComplete")
        }
    }


    fun executeGetCryptoListUseCase(){
        Timber.d("executeGetCryptoListUseCase: ")
        tx.onNext(CryptoListResult.GetCryptoListResult.InProgress)
        getCryptoListUseCase.execute(object : DisposableSingleObserver<List<DomainCrypto>>(){
            override fun onSuccess(t: List<DomainCrypto>) {
                Timber.d("onSuccess")
                tx.onNext(CryptoListResult.GetCryptoListResult.OnSuccess( with(mapper){t.toUIModel()}))
            }

            override fun onError(e: Throwable) {
                Timber.d("onError")
                tx.onNext(CryptoListResult.GetCryptoListResult.OnError(e))
            }
        })
    }


    fun dispose(){
        getCryptoListUseCase.dispose()
    }
}