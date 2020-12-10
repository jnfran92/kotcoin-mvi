package com.jnfran92.data.supplier.cloud

import com.jnfran92.data.data.crypto.Crypto
import io.reactivex.Observable

/**
 * API interface for retrieving Crypto Entities [Crypto]s from Cloud API
 * It establishes : Get and GetList methods
 */
interface CryptoApi {
    /**
     * returns an Observable with a [Crypto] object (id needed).
     */
    fun getCryptoById(cryptoId:Int):Observable<Crypto>

    /**
     * Get Crypto List, returns an Observable with a list of [Crypto] objects.
     */
    fun getCryptoList(): Observable<List<Crypto>>
}