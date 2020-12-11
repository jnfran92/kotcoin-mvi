package com.jnfran92.kotcoin.presentation.crypto.model

data class UICrypto(
    val cryptoId:Int,
    val name:String,
    val symbol:String,
    val slug:String,
    val price:Double,
    val marketCap:Double,
    val lastUpdated:String
)