package com.jnfran92.kotcoin.presentation.crypto.dataflow.action

sealed class CryptoDetailsAction {
    class GetCryptoDetails(val itemId: Int) : CryptoDetailsAction()
}