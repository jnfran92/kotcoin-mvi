package com.jnfran92.kotcoin.presentation.crypto

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jnfran92.domain.crypto.usecase.GetCryptoListUseCase
import com.jnfran92.kotcoin.KotcoinApp
import com.jnfran92.kotcoin.presentation.crypto.dataflow.intent.CryptoListIntent
import com.jnfran92.kotcoin.presentation.crypto.dataflow.interpreter.CryptoListInterpreter
import com.jnfran92.kotcoin.presentation.crypto.mapper.DomainCryptoToUIMapper
import com.jnfran92.kotcoin.presentation.crypto.dataflow.processor.CryptoListProcessor
import com.jnfran92.kotcoin.presentation.crypto.dataflow.reducer.CryptoListReducer
import com.jnfran92.kotcoin.presentation.crypto.dataflow.uistate.CryptoListUIState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class CryptoListViewModel(application: Application): AndroidViewModel(application) {

    @Inject lateinit var useCase: GetCryptoListUseCase
    @Inject lateinit var mapper: DomainCryptoToUIMapper

    lateinit var processor: CryptoListProcessor
    lateinit var interpreter: CryptoListInterpreter
    lateinit var reducer: CryptoListReducer

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
        initInjection()
        initDataFlow()
    }

    private fun initInjection() {
        Timber.d("initInjection")
        val appComponent = (getApplication() as KotcoinApp).applicationComponent
        appComponent.inject(this)

        processor = CryptoListProcessor(useCase, mapper)
        interpreter = CryptoListInterpreter()
        reducer = CryptoListReducer()
    }

    private fun initDataFlow() {
        Timber.d("initMviFlow: ")
        compositeDisposable.add(
            interpreter
                .toObservable()
                .compose(processor)
                .scan(CryptoListUIState.ShowDefaultView, reducer)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(tx::postValue) { Timber.d("initMviFlow: error $it") }
        )
        //lazy init
        rx(CryptoListIntent.GetCryptoListIntent)
    }

    override fun onCleared() {
        super.onCleared()
        this.compositeDisposable.dispose()
    }
}
