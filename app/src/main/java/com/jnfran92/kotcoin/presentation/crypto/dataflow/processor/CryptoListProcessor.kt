package com.jnfran92.kotcoin.presentation.crypto.dataflow.processor

import com.jnfran92.domain.crypto.usecase.GetCryptoListUseCase
import com.jnfran92.kotcoin.presentation.crypto.dataflow.action.CryptoListAction
import com.jnfran92.kotcoin.presentation.crypto.mapper.DomainCryptoToUIMapper
import com.jnfran92.kotcoin.presentation.crypto.dataflow.result.CryptoListResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import timber.log.Timber
import javax.inject.Inject

@ActivityRetainedScoped
class CryptoListProcessor @Inject constructor(
    private val useCase: GetCryptoListUseCase,
    private val toUIMapper: DomainCryptoToUIMapper): ObservableTransformer<CryptoListAction, CryptoListResult> {

    override fun apply(upstream: Observable<CryptoListAction>): ObservableSource<CryptoListResult> {
        Timber.d("apply")
        return upstream.flatMap { action ->
            Timber.d("apply: action $action")
            when(action){
                CryptoListAction.GetCryptoList -> {
                    useCase
                        .toSingle()
                        .map (toUIMapper::transform)
                        .toObservable()
                        .map(CryptoListResult.GetCryptoListResult::OnSuccess)
                        .cast(CryptoListResult::class.java)
                        .startWith(CryptoListResult.GetCryptoListResult.InProgress)
                        .onErrorReturn(CryptoListResult.GetCryptoListResult::OnError)
                }
            }
        }
    }
}