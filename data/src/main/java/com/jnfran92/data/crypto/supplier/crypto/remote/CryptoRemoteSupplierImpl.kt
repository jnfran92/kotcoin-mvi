package com.jnfran92.data.crypto.supplier.crypto.remote

import com.jnfran92.data.crypto.model.crypto.remote.CryptoRemote
import com.jnfran92.data.crypto.model.crypto.remote.api.DefaultApiRequest
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
class CryptoRemoteSupplierImpl @Inject constructor(private val retrofit: Retrofit) :
    CryptoRemoteSupplier {

    private val cryptoApi = this.retrofit.create(CryptoRetrofitService::class.java)

    override fun getCryptoById(cryptoId: Int): Single<CryptoRemote> {
        throw NotImplementedError("Just from local")
    }

    override fun getCryptoList(): Single<List<CryptoRemote>> {
        Timber.d("getCryptoList: ")
        return Single.create { emitter ->
            Timber.d("getCryptoList: start single")
            val call: Call<DefaultApiRequest<CryptoRemote>> = cryptoApi.requestCryptoList(100)
            call.enqueue(object : Callback<DefaultApiRequest<CryptoRemote>> {
                override fun onFailure(
                    call: Call<DefaultApiRequest<CryptoRemote>>,
                    t: Throwable
                ) {
                    Timber.d("onFailure: $t")
                    emitter.onError(t)
                }

                override fun onResponse(
                    call: Call<DefaultApiRequest<CryptoRemote>>,
                    response: Response<DefaultApiRequest<CryptoRemote>>
                ) {
                    Timber.d("onResponse: response $response")
                    val cryptoRemoteEntityList: ArrayList<CryptoRemote> =
                        response.body()?.data ?: ArrayList()
                    Timber.d("onResponse: $cryptoRemoteEntityList")
                    emitter.onSuccess(cryptoRemoteEntityList)
                }
            })
        }
    }
}