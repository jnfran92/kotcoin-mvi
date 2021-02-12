package com.jnfran92.data.crypto.mapper

import com.jnfran92.data.crypto.model.crypto.CryptoDetails
import com.jnfran92.domain.crypto.model.DomainCryptoDetails
import com.jnfran92.domain.crypto.model.DomainPrice
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoDetailsToDomainMapper @Inject constructor() {

    fun transform(item: CryptoDetails): DomainCryptoDetails {
        return DomainCryptoDetails(
            id = item.id,
            name = item.name,
            symbol = item.symbol,
            slug = item.slug,
            usdPrices = item.usdPrices.map {
                DomainPrice(
                    price = it.price,
                    volume24h = it.volume24h,
                    percentChange24h = it.percentChange24h,
                    percentChange7d = it.percentChange7d,
                    percentChange1h = it.percentChange1h,
                    lastUpdated = it.lastUpdated,
                    marketCap = it.marketCap
                ) }
        )
    }

    fun transform(items: List<CryptoDetails>): List<DomainCryptoDetails> = items.map(::transform)
}