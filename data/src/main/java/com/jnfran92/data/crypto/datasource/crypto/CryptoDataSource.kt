package com.jnfran92.data.crypto.datasource.crypto

import com.jnfran92.data.crypto.model.crypto.Crypto
import com.jnfran92.data.crypto.model.crypto.CryptoDetails
import com.jnfran92.data.crypto.model.crypto.remote.CryptoRemote
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Contract interface for all data sources that provides [CryptoRemote] objects.
 */
interface CryptoDataSource {
    fun getCryptoById(cryptoId: Long): Single<CryptoDetails>
    fun getCryptoList(): Single<List<Crypto>>
    fun saveCrypto(cryptoRemote: Crypto): Completable
    fun saveCryptoList(cryptoRemoteList: List<Crypto>): Completable
}