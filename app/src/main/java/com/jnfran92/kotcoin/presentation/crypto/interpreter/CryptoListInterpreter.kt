package com.jnfran92.kotcoin.presentation.crypto.interpreter

import com.jnfran92.kotcoin.presentation.crypto.action.CryptoListAction
import com.jnfran92.kotcoin.presentation.crypto.intent.CryptoListIntent
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

class CryptoListInterpreter @Inject constructor(){

    private val publishSubject: PublishSubject<CryptoListIntent> = PublishSubject.create()
    private val interpreter = publishSubject.flatMap {
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


}