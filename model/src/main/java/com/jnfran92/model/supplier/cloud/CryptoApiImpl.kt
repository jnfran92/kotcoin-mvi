package com.jnfran92.model.supplier.cloud

import com.jnfran92.model.entity.CryptoEntity
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * REST API Implementation for request Crypto Entities from Cloud
 */
class CryptoApiImpl @Inject constructor(private val retrofit: Retrofit):CryptoApi {

    private val cryptoApi = this.retrofit.create(CryptoRetrofitService::class.java)

    override fun getCryptoEntityById(cryptoId: Int): Observable<CryptoEntity> {
        throw NotImplementedError()
    }

    override fun getCryptoList(): Observable<List<CryptoEntity>> {
        return this.cryptoApi.requestCryptoList()
    }
}