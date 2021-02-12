package com.jnfran92.data.crypto.supplier.crypto.remote

import com.jnfran92.data.crypto.model.crypto.remote.CryptoRemote
import com.jnfran92.data.crypto.model.crypto.remote.api.DefaultApiRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit declaration for consuming Crypto API
 */
interface CryptoRetrofitService {

    @GET("")
    fun requestCrypto(@Path("id") cryptoId: Int): Call<DefaultApiRequest<CryptoRemote>>

    @GET("/v1/cryptocurrency/listings/latest")
    fun requestCryptoList(@Query("limit") limit: Int)
            : Call<DefaultApiRequest<CryptoRemote>>

    @GET("/v1/cryptocurrency/listings/latest")
    fun requestCryptoList(): Call<DefaultApiRequest<CryptoRemote>>
}