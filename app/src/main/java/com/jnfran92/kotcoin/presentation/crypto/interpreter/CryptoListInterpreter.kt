package com.jnfran92.kotcoin.presentation.crypto.interpreter

import com.jnfran92.kotcoin.presentation.crypto.action.CryptoListAction
import com.jnfran92.kotcoin.presentation.crypto.intent.CryptoListIntent
import javax.inject.Inject

class CryptoListInterpreter @Inject constructor(){

    fun interpret(intent: CryptoListIntent): CryptoListAction{
       return when(intent){
            CryptoListIntent.getCryptoListIntent -> {CryptoListAction.getCryptoList}
            is CryptoListIntent.reloadCryptoList -> {CryptoListAction.noAvailableAction}
            is CryptoListIntent.updateCryptoItemIntent -> {CryptoListAction.noAvailableAction}
            is CryptoListIntent.getCryptoItemDetailsIntent -> {CryptoListAction.getCryptoItemDetails(intent.itemId)}
        }
    }
}