package com.jnfran92.kotcoin.presentation.crypto.processor

import com.jnfran92.domain.crypto.usecase.GetCryptoListUseCase
import com.jnfran92.kotcoin.presentation.crypto.action.CryptoListAction
import com.jnfran92.kotcoin.presentation.crypto.mapper.DomainCryptoToUIMapper
import com.jnfran92.kotcoin.presentation.crypto.result.CryptoListResult
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class CryptoListProcessor @Inject constructor(
    private val getCryptoListUseCase: GetCryptoListUseCase,
    private val domainCryptoToUIMapper: DomainCryptoToUIMapper) {

    private val processor = ObservableTransformer<CryptoListAction, CryptoListResult> { actions ->
        actions.flatMap { action ->
            when(action){
                CryptoListAction.GetCryptoList -> {
                    getCryptoListUseCase.useCase
                        .map (domainCryptoToUIMapper::transform)
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