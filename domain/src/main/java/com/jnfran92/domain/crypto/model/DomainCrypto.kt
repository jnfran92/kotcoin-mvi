package com.jnfran92.domain.crypto.model

data class DomainCrypto (
    val cryptoId:Int,
    val name:String,
    val symbol:String,
    val slug:String,
    val price:Double,
    val marketCap:Double,
    val lastUpdated:String
)