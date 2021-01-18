package com.jnfran92.data.crypto.datasource.crypto

import com.jnfran92.data.crypto.model.crypto.Crypto
import com.jnfran92.data.crypto.model.crypto.Currency
import com.jnfran92.data.crypto.model.crypto.Quote
import com.jnfran92.data.crypto.model.crypto.local.CryptoLocal
import com.jnfran92.data.crypto.model.crypto.local.CryptoWithHistoricUsdPrice
import com.jnfran92.data.crypto.model.crypto.local.UsdPrice
import com.jnfran92.data.crypto.supplier.crypto.local.CryptoDao
import io.reactivex.Completable
import io.reactivex.Single
import timber.log.Timber


class LocalCryptoDataSource(private val cryptoDao: CryptoDao) : CryptoDataSource {

    override fun getCryptoById(cryptoId: Long): Single<Crypto> {
        Timber.d("getCryptoById")
        throw NotImplementedError()
    }

    override fun getCryptoList(): Single<List<Crypto>> {
        Timber.d("getCryptoList")
        return this.cryptoDao.getAllCrypto().map {
            it.map{ cryptoLocal ->
                Crypto(
                cryptoId = cryptoLocal.cryptoId!!,
                name = cryptoLocal.name,
                quoteEntity = Quote(Currency(12.0,12.0,"asdas")),
                slug = cryptoLocal.slug,
                symbol = cryptoLocal.symbol)
            }
        }
    }

    override fun saveCrypto(crypto: Crypto): Single<Long> {
        Timber.d("saveCrypto")
        return Single.create{
            val id = cryptoDao.addCrypto(
                CryptoLocal(
                    cryptoId = null,
                    symbol = crypto.symbol,
                    slug = crypto.slug,
                    name = crypto.name
                )
            )
            Timber.d("saveCrypto: created id $id")
            it.onSuccess(id)
        }
    }

    override fun saveCryptoList(cryptoList: List<Crypto>): Single<List<Long>> {
        Timber.d("saveCrypto")
        return Single.create{
            val ids = cryptoDao.addCryptoList(
                cryptoList.map { crypto ->
                    CryptoLocal(
                        cryptoId = crypto.cryptoId,
                        symbol = crypto.symbol,
                        slug = crypto.slug,
                        name = crypto.name
                    )
                }
            )
            Timber.d("saveCryptoList: created ids $ids")
            it.onSuccess(ids)
        }
    }
}