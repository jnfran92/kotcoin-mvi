package com.jnfran92.data.crypto.supplier.cloud

import com.jnfran92.data.crypto.model.crypto.Crypto
import com.jnfran92.data.crypto.model.api.DefaultApiRequest
import com.jnfran92.data.crypto.model.crypto.Quote
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit declaration for consuming Crypto API
 */
interface CryptoRetrofitService {

    @GET("")
    fun requestCrypto(@Path("id") cryptoId:Int): Call<DefaultApiRequest<Crypto>>

    @GET("/v1/cryptocurrency/listings/latest")
    fun requestCryptoList(@Query("limit") limit:Int)
            : Call<DefaultApiRequest<Crypto>>

    @GET("/v1/cryptocurrency/listings/latest")
    fun requestCryptoList(): Call<DefaultApiRequest<Crypto>>
}