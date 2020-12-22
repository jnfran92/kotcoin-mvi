package com.jnfran92.kotcoin.presentation.crypto.dataflow.action

sealed class CryptoListAction {
    object GetCryptoList : CryptoListAction()
}