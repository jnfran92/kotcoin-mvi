package com.jnfran92.kotcoin.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jnfran92.kotcoin.R
import com.jnfran92.kotcoin.di.component.DaggerApplicationComponent
import com.jnfran92.kotcoin.di.module.ApplicationModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val client = OkHttpClient.Builder()
//            .connectTimeout(10, TimeUnit.SECONDS)
//            .writeTimeout(10, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
//            .addInterceptor{chain ->
//                val original = chain.request()
//                val requestBuilder = original.newBuilder()
//                    .header("X-CMC_PRO_API_KEY", "bacdbc14-d7d9-4a0c-8ec5-77351a6be042")
//                val request = requestBuilder.build()
//                chain.proceed(request)
//            }
//
//        val okHttpClient = client.build()


        val applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(application))
            .build()

        val cryptoModel = applicationComponent.cryptoModel()


        cryptoModel.getCryptoList()
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




//
//        cryptoApi.getCryptoList()
//            .observeOn(Schedulers.io())
//            .subscribeOn(AndroidSchedulers.mainThread())
//            .subscribeBy(
//                onComplete = { println("onComplete")},
//                onError = { println("onComplete")},
//                onNext = {
//                    run{
//                    println("onNext")
//                    for (i in it){
//                        println("-----")
//                        println(i.name + " " + i.symbol + "   USD $" + i.quoteEntity.usd.price)
//                    }
//                }
//                }
//            )

    }
}
