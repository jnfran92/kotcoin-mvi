package com.jnfran92.kotcoin.presentation.crypto.mvi.intent

sealed class CryptoListIntent {
    object GetCryptoListIntent : CryptoListIntent()
}