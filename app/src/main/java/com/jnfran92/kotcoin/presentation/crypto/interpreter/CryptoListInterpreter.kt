package com.jnfran92.kotcoin.presentation.crypto.interpreter

import com.jnfran92.kotcoin.presentation.crypto.action.CryptoListAction
import com.jnfran92.kotcoin.presentation.crypto.intent.CryptoListIntent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

class CryptoListInterpreter @Inject constructor(){

    private val compositeDisposable = CompositeDisposable()
    private val publishSubject: PublishSubject<CryptoListAction> = PublishSubject.create()



    fun processIntent(intent: CryptoListIntent){
        publishSubject.onNext(intent.toAction())
    }

    private fun CryptoListIntent.toAction(): CryptoListAction{
       return when(this){
            CryptoListIntent.getCryptoListIntent -> {CryptoListAction.getCryptoList}
            is CryptoListIntent.reloadCryptoList -> {CryptoListAction.noAvailableAction}
            is CryptoListIntent.updateCryptoItemIntent -> {CryptoListAction.noAvailableAction}
            is CryptoListIntent.getCryptoItemDetailsIntent -> {CryptoListAction.getCryptoItemDetails(itemId)}
        }
    }


    fun bind(actionsObserver: DisposableObserver<CryptoListAction>){
        this.compositeDisposable.add(publishSubject.subscribeWith(actionsObserver))
    }

    fun unBind(){
        Timber.d("onDispose: ")
        compositeDisposable.dispose()
    }

}