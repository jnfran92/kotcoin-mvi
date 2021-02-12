package com.jnfran92.kotcoin.presentation.crypto.mapper

import com.jnfran92.domain.crypto.model.DomainCryptoDetails
import com.jnfran92.kotcoin.presentation.crypto.model.UICryptoDetails
import com.jnfran92.kotcoin.presentation.crypto.model.UIPrice
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DomainCryptoDetailsToUIMapper @Inject constructor() {

    fun transform(item: DomainCryptoDetails): UICryptoDetails{
        return UICryptoDetails(
            id = item.id,
            slug = item.slug,
            symbol = item.symbol,
            name = item.name,
            usdPrices = item.usdPrices.map {
                UIPrice(
                    price = it.price,
                    marketCap = it.marketCap,
                    lastUpdated = it.lastUpdated,
                    volume24h = it.volume24h,
                    percentChange24h = it.percentChange24h,
                    percentChange7d = it.percentChange7d,
                    percentChange1h = it.percentChange1h
                )
            }
        )
    }

    fun transform(items: List<DomainCryptoDetails>) : List<UICryptoDetails> = items.map(::transform)
}