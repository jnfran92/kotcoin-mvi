package com.jnfran92.data.crypto.supplier.crypto.remote

import android.accounts.NetworkErrorException
import com.jnfran92.data.crypto.model.api.DefaultApiRequest
import com.jnfran92.data.crypto.model.crypto.Crypto
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject

/**
 * REST API Implementation for request Crypto Entities from Cloud
 */
class CryptoRemoteImpl @Inject constructor(private val retrofit: Retrofit):CryptoRemote {

    private val cryptoApi = this.retrofit.create(CryptoRetrofitService::class.java)

    override fun getCryptoById(cryptoId: Int): Single<Crypto> {
        throw NotImplementedError("Just from local")
    }

    override fun getCryptoList(): Single<List<Crypto>> {
        return Single.create { emitter ->

            val call: Call<DefaultApiRequest<Crypto>> = cryptoApi.requestCryptoList(30)
            call.enqueue(object : Callback<DefaultApiRequest<Crypto>> {
                override fun onFailure(
                    call: Call<DefaultApiRequest<Crypto>>,
                    t: Throwable
                ) {
                    emitter.onError(NetworkErrorException("Failed to retrieve data."))
                }

                override fun onResponse(
                    call: Call<DefaultApiRequest<Crypto>>,
                    response: Response<DefaultApiRequest<Crypto>>) {
                    Timber.d("onResponse: response $response")
                    val cryptoEntityList: ArrayList<Crypto> =
                        response.body()?.data ?: ArrayList()
                    Timber.d("onResponse: $cryptoEntityList")
                    emitter.onSuccess(cryptoEntityList)
                }
            })
        }
    }
}