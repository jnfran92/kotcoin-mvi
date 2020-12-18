package com.jnfran92.kotcoin.presentation.crypto.processor

import com.jnfran92.domain.crypto.usecase.GetCryptoListUseCase
import com.jnfran92.kotcoin.presentation.crypto.action.CryptoListAction
import com.jnfran92.kotcoin.presentation.crypto.result.CryptoListResult
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class CryptoListProcessor @Inject constructor(useCase: GetCryptoListUseCase) {

    val compositeDisposable = CompositeDisposable()

    lateinit var input: DisposableObserver<CryptoListAction>
    lateinit var output: PublishSubject<CryptoListResult>

    fun init(){



    }


}