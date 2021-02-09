package com.jnfran92.data.crypto.datasource.crypto

import com.jnfran92.data.crypto.model.crypto.CryptoDetails
import com.jnfran92.data.crypto.model.crypto.remote.CryptoRemote
import com.jnfran92.data.crypto.model.crypto.remote.CurrencyRemote
import com.jnfran92.data.crypto.model.crypto.remote.QuoteRemote
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
                    historicUsdPriceLocal = prices.map { it.value }
                )
            )
        }
    }

    override fun getCryptoList(): Single<List<CryptoRemote>> {
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
                CryptoRemote(
                    cryptoId = cryptoLocal.cryptoId,
                    name = cryptoLocal.name,
                    quoteRemoteEntity = QuoteRemote(CurrencyRemote(cryptoPrice.value,cryptoPrice.marketCap,cryptoPrice.date)),
                    slug = cryptoLocal.slug,
                    symbol = cryptoLocal.symbol)
            }
            emitter.onSuccess(mappedResults)
        }
    }

    override fun saveCrypto(cryptoRemote: CryptoRemote): Completable {
        Timber.d("saveCrypto")
        throw NotImplementedError()
    }

    override fun saveCryptoList(cryptoRemoteList: List<CryptoRemote>): Completable {
        Timber.d("saveCryptoList")
        return Completable.create {emitter ->
            Timber.d("saveCryptoList: saving data")
            val mappedInput = cryptoRemoteList.map {
                CryptoLocal(
                    cryptoId = it.cryptoId,
                    symbol = it.symbol,
                    slug = it.slug,
                    name = it.name
                )
            }

            val prices = cryptoRemoteList.map {
                UsdPriceLocal(
                    null,
                    it.quoteRemoteEntity.usd.price,
                    it.quoteRemoteEntity.usd.lastUpdated,
                    it.quoteRemoteEntity.usd.marketCap,
                    it.cryptoId
                )
            }

            this.cryptoDao.addCryptoList(mappedInput)
            this.cryptoDao.addUsdPriceList(prices)
            emitter.onComplete()
        }
    }
}