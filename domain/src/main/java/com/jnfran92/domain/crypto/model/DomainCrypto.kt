package com.jnfran92.domain.crypto.model

data class DomainCrypto(
    val id: Long,
    var name: String,
    var symbol: String,
    var slug: String,
    var tags: List<String>,
    var cmcRank: Int,
    var circulatingSupply: Int,
    var totalSupply: Int,
    var maxSupply: Int,
    val usdPrice: DomainPrice
)