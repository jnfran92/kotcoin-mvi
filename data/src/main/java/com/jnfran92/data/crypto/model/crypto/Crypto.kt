package com.jnfran92.data.crypto.model.crypto

data class Crypto(
    val id: Long,
    var name: String,
    var symbol: String,
    var slug: String,
    var tags: List<String>,
    var cmcRank: Int,
    var circulatingSupply: Double,
    var totalSupply: Double,
    var maxSupply: Double,
    val usdPrice: Price
)