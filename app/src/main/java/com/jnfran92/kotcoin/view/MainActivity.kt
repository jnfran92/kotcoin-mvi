package com.jnfran92.kotcoin.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.jnfran92.kotcoin.R
import com.jnfran92.model.supplier.cloud.CryptoApi
import com.jnfran92.model.supplier.cloud.CryptoApiImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    companion object{
        const val BASE_URL = "https://pro-api.coinmarketcap.com/"
    }


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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val cryptoApi: CryptoApi = CryptoApiImpl(retrofit)

        cryptoApi.getCryptoList()
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = { println("onComplete")},
                onError = { println("onComplete")},
                onNext = {
                    run{
                    println("onNext")
                    for (i in it){
                        println("-----")
                        println(i.name + " " + i.symbol + "   USD $" + i.quoteEntity.usd.price)
                    }
                }
                }
            )

    }
}
