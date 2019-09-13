package com.jnfran92.kotcoin.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jnfran92.kotcoin.R
import com.jnfran92.kotcoin.di.component.DaggerApplicationComponent
import com.jnfran92.kotcoin.di.module.ApplicationModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(application))
            .build()

        val cryptoModel = applicationComponent.cryptoModel()


        cryptoModel.getCryptoList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
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
