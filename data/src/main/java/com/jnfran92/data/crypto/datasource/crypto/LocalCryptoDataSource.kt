package com.jnfran92.data.crypto.datasource.crypto

import com.jnfran92.data.crypto.model.crypto.Crypto
import com.jnfran92.data.crypto.model.crypto.CryptoDetails
import com.jnfran92.data.crypto.model.crypto.Price
import com.jnfran92.data.crypto.model.crypto.local.CryptoLocal
import com.jnfran92.data.crypto.model.crypto.local.UsdPriceLocal
import com.jnfran92.data.crypto.supplier.crypto.local.CryptoDao
import io.reactivex.Completable
import io.reactivex.Single
import timber.log.Timber


class LocalCryptoDataSource(private val cryptoDao: CryptoDao) : CryptoDataSource {

    override fun getCryptoById(cryptoId: Long): Single<CryptoDetails> {
        Timber.d("getCryptoById: $cryptoId")
        return Single.create { emitter ->
            val cryptoLocal = this.cryptoDao.getCryptoById(cryptoId)
            val prices = this.cryptoDao.getUsdPricesByCryptoId(cryptoId)
            emitter.onSuccess(
                CryptoDetails(
                    name = cryptoLocal.name,
                    symbol = cryptoLocal.symbol,
                    id = cryptoLocal.cryptoId,
                    historicUsdPriceLocal = prices.map { it.value },
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
            val mappedResults = results.map{ cryptoLocal ->
                val cryptoPrices = cryptoDao
                    .getUsdPricesByCryptoId(cryptoLocal.cryptoId)
                Timber.d("getCryptoList: prices of crypto ${cryptoLocal.name}: $cryptoPrices")
                val cryptoPrice = cryptoPrices.lastOrNull() ?: UsdPriceLocal(
                    -1,0.0,"",0.0, cryptoLocal.cryptoId)
                Crypto(
                    id = cryptoLocal.cryptoId,
                    name = cryptoLocal.name,
                    symbol = cryptoLocal.symbol,
                    usdPrice = Price(
                         price = cryptoPrice.value,
                        marketCap = cryptoPrice.marketCap,
                        lastUpdated = cryptoPrice.date,
                        percentChange1h = 0.0,
                        percentChange7d = 0.0,
                        percentChange24h = 0.0,
                        volume24h = 0.0),
                    slug = cryptoLocal.slug,
                    circulatingSupply = 0,
                    cmcRank = 0,
                    maxSupply = 0,
                    tags = listOf(),
                    totalSupply = 0
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
        return Completable.create {emitter ->
            Timber.d("saveCryptoList: saving data")
            val mappedInput = cryptoRemoteList.map {
                CryptoLocal(
                    cryptoId = it.id,
                    symbol = it.symbol,
                    slug = it.slug,
                    name = it.name
                )
            }

            val prices = cryptoRemoteList.map {
                UsdPriceLocal(
                    usdPriceId = null,
                    value =  it.usdPrice.price,
                    date = it.usdPrice.lastUpdated,
                    marketCap =  it.usdPrice.marketCap,
                    cryptoLocalId = it.id
                )
            }

            this.cryptoDao.addCryptoList(mappedInput)
            this.cryptoDao.addUsdPriceList(prices)
            emitter.onComplete()
        }
    }
}