package com.jnfran92.kotcoin.presentation.crypto.interpreter

import com.jnfran92.kotcoin.presentation.crypto.action.CryptoListAction
import com.jnfran92.kotcoin.presentation.crypto.intent.CryptoListIntent
import com.jnfran92.kotcoin.presentation.crypto.processor.CryptoListProcessor
import com.jnfran92.kotcoin.presentation.crypto.reducer.CryptoListReducer
import com.jnfran92.kotcoin.presentation.crypto.result.CryptoListResult
import com.jnfran92.kotcoin.presentation.crypto.uistate.CryptoListUIState
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

class CryptoListInterpreter @Inject constructor(){

    private val publishSubject: PublishSubject<CryptoListIntent> = PublishSubject.create()

    private val observable: Observable<CryptoListAction> = publishSubject.flatMap {
        Observable.create<CryptoListAction> { emitter ->
            Timber.d("interpreter: intent $it")
            when(it){
                CryptoListIntent.GetCryptoListIntent -> {
                    Timber.d("interpreter: CryptoListIntent.getCryptoListIntent: ")
                    emitter.onNext(CryptoListAction.GetCryptoList)
                }
            }
        }
    }

    fun toObservable() = observable

    fun processIntent(intent: CryptoListIntent){
        publishSubject.onNext(intent)
    }
}