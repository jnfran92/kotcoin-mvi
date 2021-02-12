package com.jnfran92.data.crypto.model.crypto

data class CryptoDetails(
    val id: Long,
    val name: String,
    val symbol: String,
    val slug: String,
    val usdPrices: List<Price>
)