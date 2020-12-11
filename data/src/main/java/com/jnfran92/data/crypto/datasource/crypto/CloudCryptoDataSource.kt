package com.jnfran92.data.crypto.datasource.crypto

import com.jnfran92.data.crypto.model.crypto.Crypto
import com.jnfran92.data.crypto.supplier.cloud.CryptoApi
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Data source for [CryptoApi]
 */
class CloudCryptoDataSource(private val cryptoApi: CryptoApi):CryptoDataSource {

    override fun getCryptoById(): Single<Crypto> {
        throw NotImplementedError("Not used for this project, " +
                "since only a list crypto-currency data will be shown.")
    }

    override fun getCryptoList(): Single<List<Crypto>> {
        return this.cryptoApi.getCryptoList()
    }
}