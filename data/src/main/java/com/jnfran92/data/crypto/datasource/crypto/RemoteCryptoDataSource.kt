package com.jnfran92.data.crypto.datasource.crypto

import com.jnfran92.data.crypto.model.crypto.Crypto
import com.jnfran92.data.crypto.model.crypto.CryptoDetails
import com.jnfran92.data.crypto.model.crypto.Price
import com.jnfran92.data.crypto.source.crypto.remote.CryptoRemoteSupplier
import io.reactivex.Completable
import io.reactivex.Single
import timber.log.Timber

/**
 * Data source for [CryptoRemote]
 */
class RemoteCryptoDataSource(private val cryptoRemoteSupplier: CryptoRemoteSupplier) :
    CryptoDataSource {

    override fun getCryptoById(cryptoId: Long): Single<CryptoDetails> {
        throw NotImplementedError(
            "Not used for this project, " +
                    "since only a list crypto-currency data will be shown."
        )
    }

    override fun getCryptoList(): Single<List<Crypto>> {
        Timber.d("getCryptoList: ")
        return this.cryptoRemoteSupplier.getCryptoList().map { list ->
            list.map {
                Crypto(
                    id = it.cryptoId,
                    name = it.name,
                    symbol = it.symbol,
                    totalSupply = it.totalSupply,
                    tags = listOf(),
                    maxSupply = it.maxSupply,
                    cmcRank = it.cmcRank,
                    circulatingSupply = it.circulatingSupply,
                    slug = it.slug,
                    usdPrice = Price(
                        price = it.quote.usd.price,
                        volume24h = it.quote.usd.volume24h,
                        percentChange24h = it.quote.usd.percentChange24h,
                        percentChange7d = it.quote.usd.percentChange7d,
                        percentChange1h = it.quote.usd.percentChange1h,
                        lastUpdated = it.quote.usd.lastUpdated,
                        marketCap = it.quote.usd.marketCap
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