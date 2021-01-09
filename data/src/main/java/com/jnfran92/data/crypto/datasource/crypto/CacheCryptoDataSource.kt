package com.jnfran92.data.crypto.datasource.crypto

import android.content.Context
import com.jnfran92.data.crypto.model.crypto.Crypto
import com.jnfran92.data.crypto.supplier.crypto.cache.CryptoCache
import io.reactivex.Completable
import io.reactivex.Single
import timber.log.Timber

/**
 * Data source for [CryptoCache]
 */
class CacheCryptoDataSource(private val context: Context,
                            private val cryptoCache: CryptoCache): CryptoDataSource{

    override fun getCryptoById(cryptoId: Long): Single<Crypto> {
        throw NotImplementedError()
    }

    override fun getCryptoList(): Single<List<Crypto>> {
        throw NotImplementedError()
    }

    override fun saveCrypto(crypto: Crypto): Completable {
        Timber.d("saveCrypto")
        throw NotImplementedError()
    }

    override fun saveCryptoList(cryptoList: List<Crypto>): Completable {
        Timber.d("saveCrypto")
        throw NotImplementedError()
    }
}