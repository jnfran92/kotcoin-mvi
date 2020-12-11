package com.jnfran92.kotcoin.presentation.crypto.mapper

import com.jnfran92.domain.crypto.model.DomainCrypto
import com.jnfran92.kotcoin.presentation.crypto.model.UICrypto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DomainCryptoToUIMapper @Inject constructor() {

    fun DomainCrypto.toUIModel(): UICrypto{
        return UICrypto(
            cryptoId = this.cryptoId,
            price =  this.price,
            lastUpdated = this.lastUpdated,
            marketCap = this.marketCap,
            slug = this.slug,
            symbol = this.symbol,
            name = this.name
        )
    }

    fun List<DomainCrypto>.toUIModel(): List<UICrypto> = this.map { it.toUIModel() }

}