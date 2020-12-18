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

//    private val publisherI: PublishSubject<CryptoListIntent> = PublishSubject.create()
//    private val interpreter = publisherI.flatMap {
//        Observable.create<CryptoListAction> { emitter ->
//            when(it){
//                CryptoListIntent.getCryptoListIntent -> {emitter.onNext(CryptoListAction.getCryptoList)}
//                is CryptoListIntent.reloadCryptoList -> {}
//                is CryptoListIntent.updateCryptoItemIntent -> {}
//                is CryptoListIntent.getCryptoItemDetailsIntent -> {}
//            }
//        }
//    }


}