package com.jnfran92.data.crypto.datasource.crypto

import com.jnfran92.data.crypto.model.crypto.remote.CryptoRemote
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Contract interface for all data sources that provides [CryptoRemote] objects.
 */
interface CryptoDataSource {
    fun getCryptoById(cryptoId: Long): Single<CryptoRemote>
    fun getCryptoList(): Single<List<CryptoRemote>>
    fun saveCrypto(cryptoRemote: CryptoRemote): Completable
    fun saveCryptoList(cryptoRemoteList: List<CryptoRemote>): Completable
}