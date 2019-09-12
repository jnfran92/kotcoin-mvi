package com.jnfran92.model.supplier.cloud

import com.google.gson.GsonBuilder
import com.jnfran92.model.entity.CryptoEntity
import io.reactivex.rxkotlin.subscribeBy
import org.junit.Assert
import org.junit.Test

import org.junit.Before
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


/**
 * Assert Tests for Consuming the API using Retrofit
 */
class CryptoApiImplTest {

    companion object{
        const val BASE_URL = "https://pro-api.coinmarketcap.com/"
    }

    private lateinit var cryptoApi:CryptoApi

    private val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor{chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("X-CMC_PRO_API_KEY", "bacdbc14-d7d9-4a0c-8ec5-77351a6be042")
            val request = requestBuilder.build()
            chain.proceed(request)
        }

    private val okHttpClient = client.build()


    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Before
    fun setup(){
        this.cryptoApi = CryptoApiImpl(this.retrofit)
    }

    @Test
    fun getCryptoList() {

        var cryptoList: ArrayList<CryptoEntity> = ArrayList()
        var flagError = false
        var flagComplete = false

        this.cryptoApi.getCryptoList().subscribeBy(
            onComplete = {flagComplete = true},
            onError = { e -> run{
                println("onError")
                println(e.message)
                flagError = true
            } },
            onNext = {cryptoList = it as ArrayList<CryptoEntity>}
        )

        println(cryptoList.size)


        Assert.assertTrue(cryptoList.size > 0)
        Assert.assertFalse(flagError)
        Assert.assertTrue(flagComplete)

    }
}
