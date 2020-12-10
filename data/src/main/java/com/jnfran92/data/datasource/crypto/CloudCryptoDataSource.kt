package com.jnfran92.data.datasource.crypto

import com.jnfran92.data.data.crypto.Crypto
import com.jnfran92.data.supplier.cloud.CryptoApi
import io.reactivex.Observable

/**
 * Data source for [CryptoApi]
 */
class CloudCryptoDataSource(private val cryptoApi: CryptoApi):CryptoDataSource {

    override fun getCryptoById(): Observable<Crypto> {
        throw NotImplementedError("Not used for this project, " +
                "since only a list crypto-currency data will be shown.")
    }

    override fun getCryptoList(): Observable<List<Crypto>> {
        return this.cryptoApi.getCryptoList()
    }
}