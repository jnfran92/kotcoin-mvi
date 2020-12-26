package com.jnfran92.data.crypto.datasource.crypto

import android.content.Context
import com.jnfran92.data.crypto.model.crypto.Crypto
import com.jnfran92.data.crypto.supplier.crypto.cache.CryptoCache
import io.reactivex.Single

/**
 * Data source for [CryptoCache]
 */
class CacheCryptoDataSource(private val context: Context,
                            private val cryptoCache: CryptoCache): CryptoDataSource{
    override fun getCryptoById(): Single<Crypto> {
        throw NotImplementedError()
    }

    override fun getCryptoList(): Single<List<Crypto>> {
        throw NotImplementedError()
    }

}