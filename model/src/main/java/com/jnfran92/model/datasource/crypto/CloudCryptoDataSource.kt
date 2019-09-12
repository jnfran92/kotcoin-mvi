package com.jnfran92.model.datasource.crypto

import android.content.Context
import com.jnfran92.model.data.crypto.Crypto
import com.jnfran92.model.supplier.cloud.CryptoApi
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