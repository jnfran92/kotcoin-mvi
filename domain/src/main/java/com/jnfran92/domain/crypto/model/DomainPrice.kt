package com.jnfran92.domain.crypto.model

data class DomainPrice(
    var price: Double,
    var marketCap: Double,
    var volume24h: Double,
    var percentChange1h: Double,
    var percentChange24h: Double,
    var percentChange7d: Double,
    var lastUpdated: String
)