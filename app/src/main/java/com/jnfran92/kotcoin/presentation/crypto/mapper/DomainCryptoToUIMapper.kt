package com.jnfran92.kotcoin.presentation.crypto.mapper

import com.jnfran92.domain.crypto.model.DomainCrypto
import com.jnfran92.kotcoin.presentation.crypto.model.UICrypto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DomainCryptoToUIMapper @Inject constructor() {

    fun transform(item: DomainCrypto): UICrypto{
        return UICrypto(
            cryptoId = item.cryptoId,
            price =  item.price,
            lastUpdated = item.lastUpdated,
            marketCap = item.marketCap,
            slug = item.slug,
            symbol = item.symbol,
            name = item.name
        )
    }

    fun transform(items: List<DomainCrypto>) : List<UICrypto> = items.map(::transform)
}