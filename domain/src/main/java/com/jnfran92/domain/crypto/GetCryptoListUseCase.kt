package com.jnfran92.domain.crypto

import com.jnfran92.domain.crypto.repository.CryptoRepository
import com.jnfran92.domain.crypto.model.DomainCrypto
import io.reactivex.Single
import javax.inject.Inject

/**
 * Get data
 */
class GetCryptoListUseCase @Inject constructor (private val repository: CryptoRepository) {
    operator fun invoke(): Single<List<DomainCrypto>>{
        return this.repository.getCryptoList()
    }
}