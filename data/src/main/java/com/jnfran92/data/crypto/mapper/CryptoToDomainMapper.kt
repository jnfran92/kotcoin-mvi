package com.jnfran92.data.crypto.mapper

import com.jnfran92.data.crypto.model.crypto.Crypto
import com.jnfran92.domain.crypto.model.DomainCrypto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoToDomainMapper @Inject constructor(){

    fun transform(item: Crypto): DomainCrypto{
        return DomainCrypto(
            cryptoId = item.id,
            name = item.name,
            symbol = item.symbol,
            slug = "",
            marketCap = 0.0,
            lastUpdated = "",
            price = item.currentPriceLocal
        )
    }

    fun transform(items: List<Crypto>): List<DomainCrypto> = items.map(::transform)
}