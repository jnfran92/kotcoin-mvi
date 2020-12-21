package com.jnfran92.kotcoin.presentation.crypto.intent

sealed class CryptoListIntent {
    object GetCryptoListIntent : CryptoListIntent()
}