package com.jnfran92.kotcoin.presentation.crypto.dataflow.intent

sealed class CryptoListIntent {
    object GetCryptoListIntent : CryptoListIntent()
}