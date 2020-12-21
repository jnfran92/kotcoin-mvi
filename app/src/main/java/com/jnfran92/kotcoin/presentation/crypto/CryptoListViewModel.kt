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

    private val compositeDisposable = CompositeDisposable()

    private val interpreter = CryptoListInterpreter()
    private val processor = CryptoListProcessor(useCase, mapper)
    private val reducer = CryptoListReducer()

    /**
     * TX: transmit UI events
     */
    val tx: MutableLiveData<CryptoListUIState> = MutableLiveData()


    init {
        initInjection()
        initDataFlow()
    }

    private fun initDataFlow() {
        Timber.d("initFlow")
        compositeDisposable.add(
            interpreter
                .toObservable()
                .compose(processor)
                .scan(CryptoListUIState.ShowDefaultView ,reducer)
                .subscribe(tx::setValue){}
        )
    }

    private fun initInjection() {
        Timber.d("initInjection")
        val appComponent = (getApplication() as KotcoinApp).applicationComponent
        appComponent.inject(this)
    }

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
