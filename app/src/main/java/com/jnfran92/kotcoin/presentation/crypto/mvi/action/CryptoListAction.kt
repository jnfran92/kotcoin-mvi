package com.jnfran92.kotcoin.presentation.crypto.mvi.action

sealed class CryptoListAction {
    object GetCryptoList : CryptoListAction()
}