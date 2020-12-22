package com.jnfran92.kotcoin.presentation.crypto

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jnfran92.kotcoin.presentation.crypto.dataflow.intent.CryptoListIntent
import com.jnfran92.kotcoin.presentation.crypto.dataflow.interpreter.CryptoListInterpreter
import com.jnfran92.kotcoin.presentation.crypto.dataflow.processor.CryptoListProcessor
import com.jnfran92.kotcoin.presentation.crypto.dataflow.reducer.CryptoListReducer
import com.jnfran92.kotcoin.presentation.crypto.dataflow.uistate.CryptoListUIState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class CryptoListViewModel @ViewModelInject constructor(
    private val processor: CryptoListProcessor,
    private val interpreter: CryptoListInterpreter,
    private val reducer: CryptoListReducer): ViewModel() {

    /**
     * RxJava Disposable
     */
    private val compositeDisposable = CompositeDisposable()

    /**
     * TX: transmit UI events
     */
    val tx: MutableLiveData<CryptoListUIState> = MutableLiveData()

    /**
     * RX: receive User intents
     */
    fun rx(intent: CryptoListIntent){
        Timber.d("rx: $intent")
        interpreter.processIntent(intent)
    }

    init {
        initDataFlow()
    }

    private fun initDataFlow() {
        Timber.d("initDataFlow: ")
        compositeDisposable.add(
            interpreter
                .toObservable()
                .compose(processor)
                .scan(CryptoListUIState.ShowDefaultView, reducer)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(tx::postValue) { Timber.d("initDataFlow:  error $it") }
        )
        //lazy init
        rx(CryptoListIntent.GetCryptoListIntent)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
