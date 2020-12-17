package com.jnfran92.kotcoin.presentation.crypto.intent

sealed class CryptoListIntent {
    object getCryptoListIntent : CryptoListIntent()
    class reloadCryptoList(itemId: Int) : CryptoListIntent()
    class updateCryptoItemIntent(itemId: Int) : CryptoListIntent()
    class getCryptoItemDetailsIntent(val itemId: Int) : CryptoListIntent()
}