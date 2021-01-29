package com.jnfran92.domain.crypto

import com.jnfran92.domain.crypto.repository.CryptoRepository
import com.jnfran92.domain.crypto.model.DomainCrypto
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

/**
 * Get data
 */
class GetCryptoDetailsUseCase @Inject constructor(
    private val repository: CryptoRepository
) {

    private fun buildUseCase(itemId: Long): Single<DomainCrypto>{
        Timber.d("buildUseCase: $itemId")
        return Single.just(DomainCrypto(-1,"Luka", "LK", "luka", 1.1, 5123123.21, "today bitches!"))
    }

    fun toSingle(itemId: Long): Single<DomainCrypto>{
        return buildUseCase(itemId)
    }
}