package com.jnfran92.model.supplier.cloud

import com.jnfran92.model.entity.CryptoEntity
import io.reactivex.Observable

/**
 * API interface for retrieving Crypto Entities [CryptoEntity]s from Cloud API
 * It establishes : Get and GetList methods
 */
interface CryptoApi {
    /**
     * returns an Observable with a [CryptoEntity] object (id needed).
     */
    fun getCryptoEntityById(cryptoId:Int):Observable<CryptoEntity>

    /**
     * Get Crypto List, returns an Observable with a list of [CryptoEntity] objects.
     */
    fun getCryptoList(): Observable<List<CryptoEntity>>
}