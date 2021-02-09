package com.jnfran92.data.crypto.supplier.crypto.remote

import com.jnfran92.data.crypto.model.crypto.remote.CryptoRemote
import io.reactivex.Single

/**
 * API interface for retrieving Crypto Entities [CryptoRemote]s from Cloud API
 * It establishes : Get and GetList methods
 */
interface CryptoRemoteSupplier {
    /**
     * returns an Observable with a [CryptoRemote] object (id needed).
     */
    fun getCryptoById(cryptoId:Int): Single<CryptoRemote>

    /**
     * Get Crypto List, returns an Observable with a list of [CryptoRemote] objects.
     */
    fun getCryptoList(): Single<List<CryptoRemote>>
}