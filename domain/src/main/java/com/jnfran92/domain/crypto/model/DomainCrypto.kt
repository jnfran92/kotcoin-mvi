package com.jnfran92.domain.crypto.model

data class DomainCrypto(
    val id: Long,
    var name: String,
    var symbol: String,
    var slug: String,
    var tags: List<String>,
    var cmcRank: Int,
    var circulatingSupply: Double,
    var totalSupply: Double,
    var maxSupply: Double,
    val usdPrice: DomainPrice
)