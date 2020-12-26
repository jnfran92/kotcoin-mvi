package com.jnfran92.data.crypto.datasource.crypto

import com.jnfran92.data.crypto.model.crypto.Crypto
import com.jnfran92.data.crypto.supplier.crypto.remote.CryptoRemote
import io.reactivex.Single

/**
 * Data source for [CryptoRemote]
 */
class RemoteCryptoDataSource(private val cryptoRemote: CryptoRemote):CryptoDataSource {

    override fun getCryptoById(): Single<Crypto> {
        throw NotImplementedError("Not used for this project, " +
                "since only a list crypto-currency data will be shown.")
    }

    override fun getCryptoList(): Single<List<Crypto>> {
        return this.cryptoRemote.getCryptoList()
    }
}