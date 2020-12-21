package com.jnfran92.kotcoin.presentation.crypto

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jnfran92.domain.crypto.usecase.GetCryptoListUseCase
import com.jnfran92.kotcoin.KotcoinApp
import com.jnfran92.kotcoin.presentation.crypto.intent.CryptoListIntent
import com.jnfran92.kotcoin.presentation.crypto.interpreter.CryptoListInterpreter
import com.jnfran92.kotcoin.presentation.crypto.mapper.DomainCryptoToUIMapper
import com.jnfran92.kotcoin.presentation.crypto.processor.CryptoListProcessor
import com.jnfran92.kotcoin.presentation.crypto.reducer.CryptoListReducer
import com.jnfran92.kotcoin.presentation.crypto.uistate.CryptoListUIState
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class CryptoListViewModel(application: Application): AndroidViewModel(application) {

    @Inject lateinit var useCase: GetCryptoListUseCase
    @Inject lateinit var mapper: DomainCryptoToUIMapper

    lateinit var processor: CryptoListProcessor
    lateinit var interpreter: CryptoListInterpreter
    lateinit var reducer: CryptoListReducer

    private val compositeDisposable = CompositeDisposable()


    init {
        initInjection()
        initMviFlow()
    }

    private fun initInjection() {
        Timber.d("initInjection")
        val appComponent = (getApplication() as KotcoinApp).applicationComponent
        appComponent.inject(this)

        processor = CryptoListProcessor(useCase, mapper)
        interpreter = CryptoListInterpreter()
        reducer = CryptoListReducer()
    }

    private fun initMviFlow() {
        Timber.d("initFlow")
        compositeDisposable.add(
            interpreter
                .toObservable()
                .compose(processor)
                .scan(CryptoListUIState.ShowDefaultView ,reducer)
                .subscribe(tx::setValue){}
        )
    }

    /**
     * TX: transmit UI events
     */
    val tx: MutableLiveData<CryptoListUIState> = MutableLiveData<CryptoListUIState>()

    /**
     * RX: receive User intents
     */
    fun rx(intent: CryptoListIntent){
        interpreter.processIntent(intent)
    }


    override fun onCleared() {
        super.onCleared()
        this.compositeDisposable.dispose()
    }
}
