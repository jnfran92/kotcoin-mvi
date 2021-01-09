package com.jnfran92.data.crypto.mapper

import com.jnfran92.data.crypto.model.crypto.Crypto
import com.jnfran92.domain.crypto.model.DomainCrypto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoToDomainMapper @Inject constructor(){

    private fun transform(item: Crypto): DomainCrypto{
        return DomainCrypto(
            cryptoId = item.cryptoId,
            name = item.name,
            symbol = item.symbol,
            slug = item.slug,
            marketCap = item.quoteEntity.usd.marketCap,
            lastUpdated = item.quoteEntity.usd.lastUpdated,
            price = item.quoteEntity.usd.price
        )
    }

    fun transform(items: List<Crypto>): List<DomainCrypto> = items.map(::transform)
}