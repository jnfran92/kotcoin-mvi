package com.jnfran92.data.crypto.datasource.crypto

import android.content.Context
import com.jnfran92.data.crypto.model.crypto.CryptoDetails
import com.jnfran92.data.crypto.model.crypto.remote.CryptoRemote
import com.jnfran92.data.crypto.supplier.crypto.cache.CryptoCacheSupplier
import io.reactivex.Completable
import io.reactivex.Single
import timber.log.Timber

/**
 * Data source for [CryptoCacheSupplier]
 */
class CacheCryptoDataSource(private val context: Context,
                            private val cryptoCacheSupplier: CryptoCacheSupplier): CryptoDataSource{

    override fun getCryptoById(cryptoId: Long): Single<CryptoDetails> {
        throw NotImplementedError()
    }

    override fun getCryptoList(): Single<List<CryptoRemote>> {
        throw NotImplementedError()
    }

    override fun saveCrypto(cryptoRemote: CryptoRemote): Completable {
        Timber.d("saveCrypto")
        throw NotImplementedError()
    }

    override fun saveCryptoList(cryptoRemoteList: List<CryptoRemote>): Completable {
        Timber.d("saveCrypto")
        throw NotImplementedError()
    }
}