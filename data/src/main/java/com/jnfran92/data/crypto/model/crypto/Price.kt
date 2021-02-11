package com.jnfran92.data.crypto.model.crypto

data class Price(
    var price: Double,
    var marketCap: Double,
    var volume24h: Long,
    var percentChange1h: Double,
    var percentChange24h: Double,
    var percentChange7d: Double,
    var lastUpdated: String
)