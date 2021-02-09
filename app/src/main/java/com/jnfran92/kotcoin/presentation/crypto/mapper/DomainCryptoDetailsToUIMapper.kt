package com.jnfran92.kotcoin.presentation.crypto.mapper

import com.jnfran92.domain.crypto.model.DomainCrypto
import com.jnfran92.domain.crypto.model.DomainCryptoDetails
import com.jnfran92.kotcoin.presentation.crypto.model.UICrypto
import com.jnfran92.kotcoin.presentation.crypto.model.UICryptoDetails
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DomainCryptoDetailsToUIMapper @Inject constructor() {

    fun transform(item: DomainCryptoDetails): UICryptoDetails{
        return UICryptoDetails(
            cryptoId = item.cryptoId,
            slug = item.slug,
            symbol = item.symbol,
            name = item.name,
            price = item.price
        )
    }

    fun transform(items: List<DomainCryptoDetails>) : List<UICryptoDetails> = items.map(::transform)
}