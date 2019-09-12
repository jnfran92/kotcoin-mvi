package com.jnfran92.model.datasource.crypto

import android.content.Context
import com.jnfran92.model.data.crypto.Crypto
import com.jnfran92.model.supplier.cache.CryptoCache
import io.reactivex.Observable

/**
 * Data source for [CryptoCache]
 */
class CacheCryptoDataSource(private val context: Context,
                            private val cryptoCache: CryptoCache): CryptoDataSource{
    override fun getCryptoById(): Observable<Crypto> {
        throw NotImplementedError()
    }

    override fun getCryptoList(): Observable<List<Crypto>> {
        throw NotImplementedError()
    }

}