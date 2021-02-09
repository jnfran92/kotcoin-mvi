package com.jnfran92.domain.crypto.model

data class DomainCryptoDetails(
    val cryptoId: Long,
    val name: String,
    val symbol: String,
    val slug: String,
    val price: List<Double>
)