package com.jnfran92.kotcoin.presentation.crypto

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jnfran92.domain.crypto.model.DomainCrypto
import com.jnfran92.domain.crypto.usecase.GetCryptoListUseCase
import com.jnfran92.kotcoin.KotcoinApp
import com.jnfran92.kotcoin.presentation.crypto.action.CryptoListAction
import com.jnfran92.kotcoin.presentation.crypto.intent.CryptoListIntent
import com.jnfran92.kotcoin.presentation.crypto.interpreter.CryptoListInterpreter
import com.jnfran92.kotcoin.presentation.crypto.mapper.DomainCryptoToUIMapper
import com.jnfran92.kotcoin.presentation.crypto.uistates.CryptoListUIState
import io.reactivex.observers.DisposableSingleObserver
import timber.log.Timber
import javax.inject.Inject

class CryptoListViewModel(application: Application): AndroidViewModel(application) {

    @Inject lateinit var getCryptoListUseCase: GetCryptoListUseCase
    @Inject lateinit var mapper: DomainCryptoToUIMapper

    @Inject lateinit var interpreter: CryptoListInterpreter

    init {
        val applicationComponent = (application as KotcoinApp).applicationComponent
        applicationComponent.inject(this)
    }


    val tx: MutableLiveData<List<CryptoListUIState>> by lazy {
        MutableLiveData<List<CryptoListUIState>>()
    }



    /**
     * Rx: Receive intents
     */
    fun rx(intent: CryptoListIntent){
        Timber.d("sendIntent: $intent")
        when(interpreter.interpret(intent)){
            CryptoListAction.getCryptoList -> { getCryptoList()}
            CryptoListAction.noAvailableAction -> {}
            is CryptoListAction.getCryptoItemDetails -> { }
        }
    }


    private fun getCryptoList(){
        Timber.d("getCryptoList: ")
        tx.postValue(listOf(
            CryptoListUIState.ShowDefaultView,
            CryptoListUIState.HideLoadingView,
            CryptoListUIState.HideErrorRetryView,
            CryptoListUIState.HideLoadingView,
            CryptoListUIState.HideDataView,
            CryptoListUIState.ShowLoadingView)
        )

        this.getCryptoListUseCase.execute(object : DisposableSingleObserver<List<DomainCrypto>>() {
            override fun onSuccess(t: List<DomainCrypto>) {
                Timber.d("onSuccess $t")
                tx.postValue(listOf(
                    CryptoListUIState.HideDefaultView,
                    CryptoListUIState.HideLoadingView,
                    CryptoListUIState.HideErrorRetryView,
                    CryptoListUIState.HideLoadingView,
                    CryptoListUIState.ShowDataView(t.map { with(mapper){it.toUIModel()} }),
                    CryptoListUIState.HideLoadingView
                ))
            }

            override fun onError(e: Throwable) {
                Timber.d("onError")
                tx.value = listOf(
                    CryptoListUIState.HideDefaultView,
                    CryptoListUIState.HideLoadingView,
                    CryptoListUIState.ShowErrorRetryView,
                    CryptoListUIState.HideLoadingView,
                    CryptoListUIState.HideDataView,
                    CryptoListUIState.HideLoadingView
                )
            }
        })
    }


    override fun onCleared() {
        super.onCleared()
        this.getCryptoListUseCase.dispose()
    }
}