package com.jnfran92.model.supplier.cloud

import com.jnfran92.model.entity.CryptoEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit declaration for consuming Crypto API
 */
interface CryptoRetrofitService {

    @GET("/v1/cryptocurrency/listings/latest")
    fun requestCryto(@Path("id") cryptoId:Int): Observable<CryptoEntity>

    @GET("/v1/cryptocurrency/listings/latest")
    fun requestCryptoList(): Observable<List<CryptoEntity>>

}