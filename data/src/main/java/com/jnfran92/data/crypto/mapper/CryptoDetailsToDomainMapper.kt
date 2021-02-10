package com.jnfran92.data.crypto.mapper

import com.jnfran92.data.crypto.model.crypto.Crypto
import com.jnfran92.data.crypto.model.crypto.CryptoDetails
import com.jnfran92.domain.crypto.model.DomainCrypto
import com.jnfran92.domain.crypto.model.DomainCryptoDetails
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoDetailsToDomainMapper @Inject constructor(){

    fun transform(item: CryptoDetails): DomainCryptoDetails{
        return DomainCryptoDetails(
            cryptoId = item.id,
            name = item.name,
            symbol = item.symbol,
            slug = item.slug,
            price = item.historicUsdPriceLocal
        )
    }

    fun transform(items: List<CryptoDetails>): List<DomainCryptoDetails> = items.map(::transform)
}