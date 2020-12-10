package com.jnfran92.kotcoin.di.module

import android.app.Application
import android.content.Context
import com.google.gson.GsonBuilder
import com.jnfran92.kotcoin.BuildConfig
import com.jnfran92.kotcoin.rx.JobExecutor
import com.jnfran92.kotcoin.rx.ObserverThread
import com.jnfran92.kotcoin.rx.SubscriberExecutor
import com.jnfran92.kotcoin.rx.UIThread
import com.jnfran92.data.CryptoModel
import com.jnfran92.data.CryptoModelImp
import com.jnfran92.data.supplier.cache.CryptoCache
import com.jnfran92.data.supplier.cache.CryptoCacheImpl
import com.jnfran92.data.supplier.cloud.CryptoApi
import com.jnfran92.data.supplier.cloud.CryptoApiImpl
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
                    .header("X-CMC_PRO_API_KEY", BuildConfig.API_TOKEN)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
        return client.build()
    }

    @Provides @Singleton
    fun subscriberThread(jobExecutor: JobExecutor): SubscriberExecutor {
        return jobExecutor
    }

    @Provides @Singleton
    fun observerThread(uiThread: UIThread): ObserverThread {
        return uiThread
    }

}