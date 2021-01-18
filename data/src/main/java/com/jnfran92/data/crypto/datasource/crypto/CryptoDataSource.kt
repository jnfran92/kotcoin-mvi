package com.jnfran92.data.crypto.datasource.crypto

import com.jnfran92.data.crypto.model.crypto.Crypto
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Contract interface for all data sources that provides [Crypto] objects.
 */
interface CryptoDataSource {
    fun getCryptoById(cryptoId: Long): Single<Crypto>
    fun getCryptoList(): Single<List<Crypto>>
    fun saveCrypto(crypto: Crypto): Single<Long>
    fun saveCryptoList(cryptoList: List<Crypto>): Single<List<Long>>
}