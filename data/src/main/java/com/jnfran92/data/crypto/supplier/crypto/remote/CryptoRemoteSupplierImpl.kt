package com.jnfran92.data.crypto.supplier.crypto.remote

import android.accounts.NetworkErrorException
import com.jnfran92.data.crypto.model.crypto.remote.api.DefaultApiRequest
import com.jnfran92.data.crypto.model.crypto.remote.CryptoRemote
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
class CryptoRemoteSupplierImpl @Inject constructor(private val retrofit: Retrofit):CryptoRemoteSupplier {

    private val cryptoApi = this.retrofit.create(CryptoRetrofitService::class.java)

    override fun getCryptoById(cryptoId: Int): Single<CryptoRemote> {
        throw NotImplementedError("Just from local")
    }

    override fun getCryptoList(): Single<List<CryptoRemote>> {
        return Single.create { emitter ->

            val call: Call<DefaultApiRequest<CryptoRemote>> = cryptoApi.requestCryptoList(30)
            call.enqueue(object : Callback<DefaultApiRequest<CryptoRemote>> {
                override fun onFailure(
                    call: Call<DefaultApiRequest<CryptoRemote>>,
                    t: Throwable
                ) {
                    emitter.onError(NetworkErrorException("Failed to retrieve data."))
                }

                override fun onResponse(
                    call: Call<DefaultApiRequest<CryptoRemote>>,
                    response: Response<DefaultApiRequest<CryptoRemote>>) {
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