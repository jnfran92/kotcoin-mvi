package com.jnfran92.data.crypto.mapper

import com.jnfran92.data.crypto.model.crypto.Crypto
import com.jnfran92.domain.crypto.model.DomainCrypto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoToDomainMapper @Inject constructor(){

    fun Crypto.toDomainModel(): DomainCrypto{
        return DomainCrypto(cryptoId = this.cryptoId,
            name = this.name,
            symbol = this.symbol,
            slug = this.slug,
            marketCap = this.quoteEntity.usd.marketCap,
            lastUpdated = this.quoteEntity.usd.lastUpdated,
            price = this.quoteEntity.usd.price)
    }

    fun List<Crypto>.toDomainModel(): List<DomainCrypto> = this.map{it.toDomainModel()}
}