package com.jnfran92.kotcoin.presentation.crypto

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jnfran92.domain.crypto.model.DomainCrypto
import com.jnfran92.domain.crypto.usecase.GetCryptoListUseCase
import com.jnfran92.kotcoin.KotcoinApp
import com.jnfran92.kotcoin.presentation.crypto.mapper.DomainCryptoToUIMapper
import com.jnfran92.kotcoin.presentation.crypto.model.UICrypto
import io.reactivex.observers.DisposableSingleObserver
import timber.log.Timber
import javax.inject.Inject

class CryptoListViewModel(application: Application): AndroidViewModel(application) {

    @Inject lateinit var getCryptoListUseCase: GetCryptoListUseCase
    @Inject lateinit var mapper: DomainCryptoToUIMapper

    init {
        val applicationComponent = (application as KotcoinApp).applicationComponent
        applicationComponent.inject(this)
    }

    val cryptoList: MutableLiveData<List<UICrypto>> by lazy{
        MutableLiveData<List<UICrypto>>().also { loadCryptoList() }
    }

    private fun loadCryptoList() {
        Timber.d("loadCryptoList")
        this.getCryptoListUseCase.execute(object : DisposableSingleObserver<List<DomainCrypto>>() {
            override fun onSuccess(t: List<DomainCrypto>) {
                Timber.d("onSuccess")
                cryptoList.value = t.map { with(mapper){it.toUIModel()} }
            }

            override fun onError(e: Throwable) {
                Timber.d("onError")
            }
        })
    }


    override fun onCleared() {
        super.onCleared()
        this.getCryptoListUseCase.dispose()
    }
}