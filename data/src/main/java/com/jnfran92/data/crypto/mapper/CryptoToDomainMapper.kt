package com.jnfran92.data.crypto.mapper

import com.jnfran92.data.crypto.model.crypto.Crypto
import com.jnfran92.domain.crypto.model.DomainCrypto
import com.jnfran92.domain.crypto.model.DomainPrice
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoToDomainMapper @Inject constructor() {

    fun transform(item: Crypto): DomainCrypto {
        return DomainCrypto(
            id = item.id,
            name = item.name,
            symbol = item.symbol,
            slug = "",
            usdPrice = with(item.usdPrice) {
                DomainPrice(
                    price = price,
                    marketCap = marketCap,
                    lastUpdated = lastUpdated,
                    percentChange1h = percentChange1h,
                    percentChange7d = percentChange7d,
                    percentChange24h = percentChange24h,
                    volume24h = volume24h
                )
            },
            circulatingSupply = item.circulatingSupply,
            cmcRank = item.cmcRank,
            maxSupply = item.maxSupply,
            tags = item.tags,
            totalSupply = item.totalSupply
        )
    }

    fun transform(items: List<Crypto>): List<DomainCrypto> = items.map(::transform)
}