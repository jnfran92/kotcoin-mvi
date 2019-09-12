package com.jnfran92.model.supplier.cloud

import com.jnfran92.model.entity.CryptoEntity
import io.reactivex.Observable

/**
 * API interface for retrieving Crypto Entitie [CryptoEntity]s from Cloud API
 * It establishes : Get and GetList methods
 */
interface CryptoApi {
    /**
     * Get Crypto
     */
    fun getCryptoEntityById(cryptoId:Int):Observable<CryptoEntity>

    /**
     * Get Crypto List
     */
    fun getCryptoList(): Observable<List<CryptoEntity>>
}