package com.jnfran92.data.crypto.datasource.crypto

import com.jnfran92.data.crypto.supplier.crypto.remote.CryptoRemoteSupplier
import io.reactivex.Completable
import io.reactivex.Single
import timber.log.Timber

/**
 * Data source for [CryptoRemote]
 */
class RemoteCryptoDataSource(private val cryptoRemoteSupplier: CryptoRemoteSupplier):CryptoDataSource {

    override fun getCryptoById(cryptoId: Long): Single<com.jnfran92.data.crypto.model.crypto.remote.CryptoRemote> {
        throw NotImplementedError("Not used for this project, " +
                "since only a list crypto-currency data will be shown.")
    }

    override fun getCryptoList(): Single<List<com.jnfran92.data.crypto.model.crypto.remote.CryptoRemote>> {
        return this.cryptoRemoteSupplier.getCryptoList()
    }

    override fun saveCrypto(cryptoRemote: com.jnfran92.data.crypto.model.crypto.remote.CryptoRemote): Completable {
        Timber.d("saveCrypto")
        throw NotImplementedError()
    }

    override fun saveCryptoList(cryptoRemoteList: List<com.jnfran92.data.crypto.model.crypto.remote.CryptoRemote>): Completable {
        Timber.d("saveCryptoList")
        throw NotImplementedError()
    }
}