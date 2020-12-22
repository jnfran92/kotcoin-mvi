package com.jnfran92.kotcoin.presentation.crypto.dataflow.result

import com.jnfran92.kotcoin.presentation.crypto.model.UICrypto

sealed class CryptoDetailsResult {

    sealed class GetCryptoDetailsResult: CryptoDetailsResult(){
        object InProgress: GetCryptoDetailsResult()
        class OnError(val t: Throwable): GetCryptoDetailsResult()
        class OnSuccess(val data: UICrypto): GetCryptoDetailsResult()
    }
}