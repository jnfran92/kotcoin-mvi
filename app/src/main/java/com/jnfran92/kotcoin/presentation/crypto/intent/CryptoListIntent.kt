package com.jnfran92.kotcoin.presentation.crypto.intent

sealed class CryptoListIntent {
    object getCryptoListIntent : CryptoListIntent()
    object reloadCryptoList : CryptoListIntent()
    class updateCryptoItemIntent(itemId: Int) : CryptoListIntent()
    class getCryptoItemDetailsIntent(val itemId: Int) : CryptoListIntent()
}