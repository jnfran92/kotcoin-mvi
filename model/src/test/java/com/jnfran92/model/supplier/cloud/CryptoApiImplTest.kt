package com.jnfran92.model.supplier.cloud

import com.google.gson.GsonBuilder
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Assert Tests for Consuming the API using Retrofit
 */
class CryptoApiImplTest {

    companion object{
        const val BASE_URL = "https://pro-api.coinmarketcap.com/"
    }

    private lateinit var cryptoApi:CryptoApi

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Before
    fun setup(){
        this.cryptoApi = CryptoApiImpl(this.retrofit)
    }

    @Test
    fun getCryptoList() {
        this.cryptoApi.getCryptoList()
        System.out.println()
    }
}
