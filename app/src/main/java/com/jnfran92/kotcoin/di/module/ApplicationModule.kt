package com.jnfran92.kotcoin.di.module

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.jnfran92.data.crypto.CryptoRepositoryImpl
import com.jnfran92.data.crypto.supplier.crypto.cache.CryptoCache
import com.jnfran92.data.crypto.supplier.crypto.cache.CryptoCacheImpl
import com.jnfran92.data.crypto.supplier.crypto.local.CryptoDao
import com.jnfran92.data.crypto.supplier.crypto.remote.CryptoRemote
import com.jnfran92.data.crypto.supplier.crypto.remote.CryptoRemoteImpl
import com.jnfran92.data.crypto.supplier.db.AppDatabase
import com.jnfran92.domain.crypto.CryptoRepository
import com.jnfran92.kotcoin.BuildConfig
import com.jnfran92.kotcoin.rx.JobExecutor
import com.jnfran92.kotcoin.rx.ObserverThread
import com.jnfran92.kotcoin.rx.SubscriberExecutor
import com.jnfran92.kotcoin.rx.UIThread
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {

    private const val BASE_URL = "https://pro-api.coinmarketcap.com/"


    @Provides @Singleton
    fun cryptoRepository(cryptoRepository: CryptoRepositoryImpl): CryptoRepository {
        return cryptoRepository
    }

    @Provides @Singleton
    fun cryptoApi(cryptoApi: CryptoRemoteImpl): CryptoRemote {
        return cryptoApi
    }

    @Provides @Singleton
    fun cryptoCache(cryptoCache: CryptoCacheImpl): CryptoCache {
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

    @Provides @Singleton
    fun appDatabase(@ApplicationContext context: Context):AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "app-database"
        ).build()
    }

    @Provides @Singleton
    fun cryptoDao(appDatabase: AppDatabase): CryptoDao{
        return appDatabase.cryptoDao()
    }
}