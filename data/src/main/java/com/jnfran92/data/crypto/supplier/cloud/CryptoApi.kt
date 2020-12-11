package com.jnfran92.data.crypto.supplier.cloud

import com.jnfran92.data.crypto.model.crypto.Crypto
import io.reactivex.Observable
import io.reactivex.Single

/**
 * API interface for retrieving Crypto Entities [Crypto]s from Cloud API
 * It establishes : Get and GetList methods
 */
interface CryptoApi {
    /**
     * returns an Observable with a [Crypto] object (id needed).
     */
    fun getCryptoById(cryptoId:Int): Single<Crypto>

    /**
     * Get Crypto List, returns an Observable with a list of [Crypto] objects.
     */
    fun getCryptoList(): Single<List<Crypto>>
}