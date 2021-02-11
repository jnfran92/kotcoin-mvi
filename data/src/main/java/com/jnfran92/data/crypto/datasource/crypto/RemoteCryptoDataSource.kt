package com.jnfran92.data.crypto.datasource.crypto

import com.jnfran92.data.crypto.model.crypto.Crypto
import com.jnfran92.data.crypto.model.crypto.CryptoDetails
import com.jnfran92.data.crypto.model.crypto.Price
import com.jnfran92.data.crypto.supplier.crypto.remote.CryptoRemoteSupplier
import io.reactivex.Completable
import io.reactivex.Single
import timber.log.Timber

/**
 * Data source for [CryptoRemote]
 */
class RemoteCryptoDataSource(private val cryptoRemoteSupplier: CryptoRemoteSupplier):CryptoDataSource {

    override fun getCryptoById(cryptoId: Long): Single<CryptoDetails> {
        throw NotImplementedError("Not used for this project, " +
                "since only a list crypto-currency data will be shown.")
    }

    override fun getCryptoList(): Single<List<Crypto>> {
        Timber.d("getCryptoList: ")
        return this.cryptoRemoteSupplier.getCryptoList().map { list ->
            list.map {
                Crypto(
                    id = it.cryptoId,
                    name = it.name,
                    symbol = it.symbol,
                    totalSupply = 0,
                    tags = listOf(),
                    maxSupply = 0,
                    cmcRank = 0,
                    circulatingSupply = 0,
                    slug = it.slug,
                    usdPrice = Price(
                        price = it.quoteRemoteEntity.usd.price,
                        volume24h = it.quoteRemoteEntity.usd.volume24h,
                        percentChange24h = it.quoteRemoteEntity.usd.percentChange24h,
                        percentChange7d = it.quoteRemoteEntity.usd.percentChange7d,
                        percentChange1h = it.quoteRemoteEntity.usd.percentChange1h,
                        lastUpdated = it.quoteRemoteEntity.usd.lastUpdated,
                        marketCap = it.quoteRemoteEntity.usd.marketCap
                    )
                )
            }
        }
    }

    override fun saveCrypto(cryptoRemote: Crypto): Completable {
        Timber.d("saveCrypto")
        throw NotImplementedError()
    }

    override fun saveCryptoList(cryptoRemoteList: List<Crypto>): Completable {
        Timber.d("saveCryptoList")
        throw NotImplementedError()
    }
}