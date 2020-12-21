package com.jnfran92.kotcoin.presentation.crypto.result

import com.jnfran92.kotcoin.presentation.crypto.model.UICrypto

sealed class CryptoListResult {

    sealed class GetCryptoListResult: CryptoListResult(){
        object InProgress: GetCryptoListResult()
        class OnError(val t: Throwable): GetCryptoListResult()
        class OnSuccess(val data: List<UICrypto>): GetCryptoListResult()
    }

    sealed class GetCryptoItemResult: CryptoListResult(){
        object InProgress: GetCryptoItemResult()
        class OnError(val t: Throwable): GetCryptoItemResult()
        class OnSuccess(val data: UICrypto): GetCryptoItemResult()
    }
}