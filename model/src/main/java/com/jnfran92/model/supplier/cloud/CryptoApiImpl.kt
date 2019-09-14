package com.jnfran92.model.supplier.cloud

import android.accounts.NetworkErrorException
import com.jnfran92.model.data.crypto.Crypto
import com.jnfran92.model.data.api.DefaultApiRequest
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * REST API Implementation for request Crypto Entities from Cloud
 */
class CryptoApiImpl @Inject constructor(private val retrofit: Retrofit):CryptoApi {

    private val cryptoApi = this.retrofit.create(CryptoRetrofitService::class.java)

    override fun getCryptoById(cryptoId: Int): Observable<Crypto> {
        throw NotImplementedError()
    }

    override fun getCryptoList(): Observable<List<Crypto>> {
        return Observable.create { emitter ->

            val call: Call<DefaultApiRequest<Crypto>> = cryptoApi.requestCryptoList(10)
            call.enqueue(object : Callback<DefaultApiRequest<Crypto>> {
                override fun onFailure(
                    call: Call<DefaultApiRequest<Crypto>>,
                    t: Throwable
                ) {
                    emitter?.onError(NetworkErrorException("Failed to retrieve data."))
                }

                override fun onResponse(
                    call: Call<DefaultApiRequest<Crypto>>,
                    response: Response<DefaultApiRequest<Crypto>>
                ) {
                    val cryptoEntityList: ArrayList<Crypto> =
                        response.body()?.cryptoEntityList ?: ArrayList()
                    emitter.onNext(cryptoEntityList)
                    emitter.onComplete()
                }
            })
        }
    }
}