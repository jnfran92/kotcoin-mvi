package com.jnfran92.kotcoin.presentation.crypto.action

sealed class CryptoListAction {
    object getCryptoList : CryptoListAction()
    class getCryptoItemDetails(val id:Int): CryptoListAction()
}