package com.jnfran92.kotcoin.presentation.crypto.dataflow.intent

sealed class CryptoDetailsIntent {
    class GetCryptoDetailsIntent(val itemId: Long) : CryptoDetailsIntent()
}