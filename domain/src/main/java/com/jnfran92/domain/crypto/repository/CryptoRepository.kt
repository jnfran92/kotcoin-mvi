package com.jnfran92.domain.crypto.repository

import com.jnfran92.domain.crypto.model.DomainCrypto
import com.jnfran92.domain.crypto.model.DomainCryptoDetails
import io.reactivex.Single


/**
 *  Business Logic contract for Managing DomainCrypto objects.
 */
interface CryptoRepository {

    /**
     * Get List of DomainCrypto objects.
     */
    fun getCryptoList(): Single<List<DomainCrypto>>

    /**
     * Get DomainCrypto by Id
     */
    fun getCryptoById(cryptoId: Long): Single<DomainCryptoDetails>

}