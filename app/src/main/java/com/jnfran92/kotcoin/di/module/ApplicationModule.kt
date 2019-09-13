package com.jnfran92.kotcoin.di.module

import android.app.Application
import android.content.Context
import com.google.gson.GsonBuilder
import com.jnfran92.model.CryptoModel
import com.jnfran92.model.CryptoModelImp
import com.jnfran92.model.supplier.cache.CryptoCache
import com.jnfran92.model.supplier.cache.CryptoCacheImpl
import com.jnfran92.model.supplier.cloud.CryptoApi
import com.jnfran92.model.supplier.cloud.CryptoApiImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    companion object{
        const val BASE_URL = "https://pro-api.coinmarketcap.com/"
    }

    @Provides @Singleton
    fun context(): Context {
        return this.application
    }

    @Provides @Singleton
    fun cryptoModel(cryptoModel: CryptoModelImp): CryptoModel{
        return cryptoModel
    }

    @Provides @Singleton
    fun cryptoApi(cryptoApi: CryptoApiImpl): CryptoApi{
        return cryptoApi
    }

    @Provides @Singleton
    fun cryptoCache(cryptoCache: CryptoCacheImpl): CryptoCache{
        return cryptoCache
    }

    @Provides @Singleton
    fun retrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }


    @Provides @Singleton
    fun okHttpClient(): OkHttpClient{
        val client = OkHttpClient.Builder()
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
        return client.build()
    }

}