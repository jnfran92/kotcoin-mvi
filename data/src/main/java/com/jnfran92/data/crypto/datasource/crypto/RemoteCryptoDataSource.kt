package com.jnfran92.data.crypto.datasource.crypto

import com.jnfran92.data.crypto.model.crypto.Crypto
import com.jnfran92.data.crypto.supplier.crypto.remote.CryptoRemote
import io.reactivex.Completable
import io.reactivex.Single
import timber.log.Timber

/**
 * Data source for [CryptoRemote]
 */
class RemoteCryptoDataSource(private val cryptoRemote: CryptoRemote):CryptoDataSource {

    override fun getCryptoById(cryptoId: Long): Single<Crypto> {
        throw NotImplementedError("Not used for this project, " +
                "since only a list crypto-currency data will be shown.")
    }

    override fun getCryptoList(): Single<List<Crypto>> {
        return this.cryptoRemote.getCryptoList()
    }

    override fun saveCrypto(crypto: Crypto): Single<Long> {
        Timber.d("saveCrypto")
        throw NotImplementedError()
    }

    override fun saveCryptoList(cryptoList: List<Crypto>): Single<List<Long>> {
        Timber.d("saveCryptoList")
        throw NotImplementedError()
    }
}