package com.jnfran92.kotcoin.presentation.crypto

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.jnfran92.domain.crypto.usecase.GetCryptoListUseCase
import com.jnfran92.kotcoin.KotcoinApp
import com.jnfran92.kotcoin.presentation.crypto.interpreter.CryptoListInterpreter
import com.jnfran92.kotcoin.presentation.crypto.mapper.DomainCryptoToUIMapper
import com.jnfran92.kotcoin.presentation.crypto.processor.CryptoListProcessor
import com.jnfran92.kotcoin.presentation.crypto.reducer.CryptoListReducer
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class CryptoListViewModel(application: Application): AndroidViewModel(application) {

    @Inject lateinit var useCase: GetCryptoListUseCase
    @Inject lateinit var mapper: DomainCryptoToUIMapper

    private val compositeDisposable = CompositeDisposable()

    val interpreter = CryptoListInterpreter()
    val processor = CryptoListProcessor(useCase, mapper)
    val reducer = CryptoListReducer()


    init {
        initInjection()

    }

    private fun initInjection() {
        Timber.d("initInjection")
        val appComponent = (getApplication() as KotcoinApp).applicationComponent
        appComponent.inject(this)
    }


    override fun onCleared() {
        super.onCleared()
        this.compositeDisposable.dispose()
    }
}
