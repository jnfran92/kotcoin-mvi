package com.jnfran92.data.crypto.datasource.crypto

import com.jnfran92.data.crypto.model.crypto.Crypto
import com.jnfran92.data.crypto.model.crypto.CryptoDetails
import com.jnfran92.data.crypto.model.crypto.Price
import com.jnfran92.data.crypto.model.crypto.local.CryptoLocal
import com.jnfran92.data.crypto.model.crypto.local.PriceLocal
import com.jnfran92.data.crypto.source.crypto.local.CryptoDao
import io.reactivex.Completable
import io.reactivex.Single
import timber.log.Timber


class LocalCryptoDataSource(private val cryptoDao: CryptoDao) : CryptoDataSource {

    override fun getCryptoById(cryptoId: Long): Single<CryptoDetails> {
        Timber.d("getCryptoById: $cryptoId")
        return Single.create { emitter ->
            val cryptoLocal = this.cryptoDao.getCryptoById(cryptoId)
            val prices = this.cryptoDao.getUsdPricesByCryptoId(cryptoId).map {
                Price(
                    price = it.price,
                    marketCap = it.marketCap,
                    lastUpdated = it.lastUpdated,
                    percentChange1h = it.percentChange1h,
                    percentChange7d = it.percentChange7d,
                    percentChange24h = it.percentChange24h,
                    volume24h = it.volume24h
                )
            }
            emitter.onSuccess(
                CryptoDetails(
                    name = cryptoLocal.name,
                    symbol = cryptoLocal.symbol,
                    id = cryptoLocal.cryptoId,
                    usdPrices = prices,
                    slug = cryptoLocal.slug
                )
            )
        }
    }

    override fun getCryptoList(): Single<List<Crypto>> {
        Timber.d("getCryptoList")
        return Single.create { emitter ->
            val results = this.cryptoDao.getAllCrypto()
            Timber.d("getCryptoList: results $results")
            val mappedResults = results.map { cryptoLocal ->
                val cryptoPrices = cryptoDao.getUsdPricesByCryptoId(cryptoLocal.cryptoId)
                Timber.d("getCryptoList: prices of crypto ${cryptoLocal.name}: $cryptoPrices")
                val cryptoPrice = cryptoPrices.lastOrNull() ?: PriceLocal(
                    price = 0.0,
                    marketCap = 0.0,
                    percentChange1h = 0.0,
                    percentChange24h = 0.0,
                    percentChange7d = 0.0,
                    volume24h = 0.0,
                    lastUpdated = "",
                    cryptoId = cryptoLocal.cryptoId,
                    usdPriceId = null
                )

                Crypto(
                    id = cryptoLocal.cryptoId,
                    name = cryptoLocal.name,
                    symbol = cryptoLocal.symbol,
                    usdPrice = Price(
                        price = cryptoPrice.price,
                        marketCap = cryptoPrice.marketCap,
                        lastUpdated = cryptoPrice.lastUpdated,
                        percentChange1h = cryptoPrice.percentChange1h,
                        percentChange7d = cryptoPrice.percentChange7d,
                        percentChange24h = cryptoPrice.percentChange24h,
                        volume24h = cryptoPrice.volume24h
                    ),
                    slug = cryptoLocal.slug,
                    circulatingSupply = cryptoLocal.circulatingSupply,
                    cmcRank = cryptoLocal.cmcRank,
                    maxSupply = cryptoLocal.maxSupply,
                    tags = listOf(),
                    totalSupply = cryptoLocal.totalSupply
                )
            }
            emitter.onSuccess(mappedResults)
        }
    }

    override fun saveCrypto(cryptoRemote: Crypto): Completable {
        Timber.d("saveCrypto")
        throw NotImplementedError()
    }

    override fun saveCryptoList(cryptoRemoteList: List<Crypto>): Completable {
        Timber.d("saveCryptoList")
        return Completable.create { emitter ->
            Timber.d("saveCryptoList: saving data")
            val mappedInput = cryptoRemoteList.map {
                CryptoLocal(
                    cryptoId = it.id,
                    symbol = it.symbol,
                    slug = it.slug,
                    name = it.name,
                    circulatingSupply = it.circulatingSupply,
                    cmcRank = it.cmcRank,
                    maxSupply = it.maxSupply,
                    totalSupply = it.totalSupply
                )
            }

            val prices = cryptoRemoteList.map {
                PriceLocal(
                    usdPriceId = null,
                    marketCap = it.usdPrice.marketCap,
                    cryptoId = it.id,
                    lastUpdated = it.usdPrice.lastUpdated,
                    volume24h = it.usdPrice.volume24h,
                    percentChange24h = it.usdPrice.percentChange24h,
                    percentChange7d = it.usdPrice.percentChange7d,
                    percentChange1h = it.usdPrice.percentChange1h,
                    price = it.usdPrice.price
                )
            }

            this.cryptoDao.addCryptoList(mappedInput)
            this.cryptoDao.addUsdPriceList(prices)
            emitter.onComplete()
        }
    }
}