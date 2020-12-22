package com.jnfran92.kotcoin.presentation.crypto.interpreter

import com.jnfran92.kotcoin.presentation.crypto.action.CryptoListAction
import com.jnfran92.kotcoin.presentation.crypto.intent.CryptoListIntent
import io.reactivex.Observable
import io.reactivex.subjects.ReplaySubject
import timber.log.Timber
import javax.inject.Inject

class CryptoListInterpreter @Inject constructor(){

    private val subject: ReplaySubject<CryptoListIntent> = ReplaySubject.create()

    private val observable: Observable<CryptoListAction> = subject.flatMap {
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
        Timber.d("processIntent: $intent")
        subject.onNext(intent)
    }
}