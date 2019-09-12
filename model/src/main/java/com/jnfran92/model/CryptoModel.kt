package com.jnfran92.model

import com.jnfran92.model.data.crypto.Crypto
import io.reactivex.Observable

/**
 *  Business Logic contract for Managing Crypto objects.
 */
interface CryptoModel {

    /**
     * Get crypto by id.
     */
    fun getCryptoById(cryptoId: Int): Observable<Crypto>

    /**
     * Get List of Crypto objects.
     */
    fun getCryptoList(): Observable<List<Crypto>>

    /**
     * Edit Crypto object.
     */
    fun editCrypto(crypto: Crypto): Observable<Void>

    /**
     * Delete Crypto Object.
     */
    fun deleteCrypto(crypto: Crypto): Observable<Void>

    /**
     * Update Crypto object.
     */
    fun updateCrypto(crypto: Crypto): Observable<Void>

}