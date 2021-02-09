package com.jnfran92.domain.crypto

import com.jnfran92.domain.crypto.repository.CryptoRepository
import com.jnfran92.domain.crypto.model.DomainCrypto
import com.jnfran92.domain.crypto.model.DomainCryptoDetails
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

/**
 * Get crypto details by id
 */
class GetCryptoDetailsUseCase @Inject constructor(private val repository: CryptoRepository) {
    operator fun invoke(cryptoId: Long): Single<DomainCryptoDetails>{
        Timber.d("invoke: id $cryptoId")
        return this.repository.getCryptoById(cryptoId)
    }
}