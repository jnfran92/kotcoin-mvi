package com.jnfran92.kotcoin.presentation.crypto.dataflow.interpreter

import com.jnfran92.kotcoin.presentation.crypto.dataflow.action.CryptoDetailsAction
import com.jnfran92.kotcoin.presentation.crypto.dataflow.action.CryptoListAction
import com.jnfran92.kotcoin.presentation.crypto.dataflow.intent.CryptoDetailsIntent
import com.jnfran92.kotcoin.presentation.crypto.dataflow.intent.CryptoListIntent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.reactivex.Observable
import io.reactivex.subjects.ReplaySubject
import timber.log.Timber
import javax.inject.Inject


class CryptoDetailsInterpreter @Inject constructor(){

    private val subject: ReplaySubject<CryptoDetailsIntent> = ReplaySubject.create()

    private val observable: Observable<CryptoDetailsAction> = subject.flatMap {
        Observable.create<CryptoDetailsAction> { emitter ->
            Timber.d("interpreter: intent $it")
            when(it){
                is CryptoDetailsIntent.GetCryptoDetailsIntent -> {
                    Timber.d("interpreter: CryptoListIntent.getCryptoListIntent: ")
                    emitter.onNext(CryptoDetailsAction.GetCryptoDetails(it.itemId))
                }
            }
        }
    }

    fun toObservable() = observable

    fun processIntent(intent: CryptoDetailsIntent){
        Timber.d("processIntent: $intent")
        subject.onNext(intent)
    }
}