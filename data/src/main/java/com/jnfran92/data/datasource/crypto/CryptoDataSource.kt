package com.jnfran92.data.datasource.crypto

import com.jnfran92.data.data.crypto.Crypto
import io.reactivex.Observable

/**
 * Contract interface for all data sources that provides [Crypto] objects.
 */
interface CryptoDataSource {
    fun getCryptoById(): Observable<Crypto>
    fun getCryptoList(): Observable<List<Crypto>>
}