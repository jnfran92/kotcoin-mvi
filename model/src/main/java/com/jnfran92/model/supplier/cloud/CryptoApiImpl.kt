package com.jnfran92.model.supplier.cloud

import android.accounts.NetworkErrorException
import com.jnfran92.model.entity.CryptoEntity
import com.jnfran92.model.entity.api.DefaultApiRequestEntity
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.rxkotlin.subscribeBy
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

    override fun getCryptoEntityById(cryptoId: Int): Observable<CryptoEntity> {
        throw NotImplementedError()
    }

    override fun getCryptoList(): Observable<List<CryptoEntity>> {
        return Observable.create { emitter ->

            val call: Call<DefaultApiRequestEntity<CryptoEntity>> = cryptoApi.requestCryptoList()
            call.enqueue(object : Callback<DefaultApiRequestEntity<CryptoEntity>> {
                override fun onFailure(
                    call: Call<DefaultApiRequestEntity<CryptoEntity>>,
                    t: Throwable
                ) {
                    emitter!!.onError(NetworkErrorException())
//                                            println("on Failure")
                }

                override fun onResponse(
                    call: Call<DefaultApiRequestEntity<CryptoEntity>>,
                    response: Response<DefaultApiRequestEntity<CryptoEntity>>
                ) {
                    val cryptoEntityList: ArrayList<CryptoEntity> =
                        response.body()?.cryptoEntityList ?: ArrayList()
                    emitter.onNext(cryptoEntityList)
                    emitter.onComplete()
                }
            })
        }
    }



//    override fun getCryptoEntityById(cryptoId: Int): Observable<CryptoEntity> {
//        throw NotImplementedError()
//    }
//
//    override fun getCryptoList(): Observable<List<CryptoEntity>> {
//        return this.cryptoApi.requestCryptoList()
//    }




}