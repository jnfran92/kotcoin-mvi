package com.jnfran92.domain.crypto

import com.jnfran92.domain.crypto.model.DomainCrypto
import io.reactivex.Single


/**
 *  Business Logic contract for Managing DomainCrypto objects.
 */
interface CryptoRepository {


    /**
     * Get List of DomainCrypto objects.
     */
    fun getCryptoList(): Single<List<DomainCrypto>>
}