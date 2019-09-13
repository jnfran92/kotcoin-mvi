package com.jnfran92.kotcoin.view

import android.os.Bundle
import com.jnfran92.kotcoin.R
import com.jnfran92.kotcoin.di.component.CryptoComponent
import com.jnfran92.kotcoin.di.component.DaggerCryptoComponent
import com.jnfran92.kotcoin.view.activity.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainActivity : BaseActivity() {

    private lateinit var cryptoComponent: CryptoComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCryptoComponent()

        val cryptoModel = cryptoComponent.cryptoModel()


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


    private fun initCryptoComponent(){
        this.cryptoComponent = DaggerCryptoComponent
            .builder()
            .activityModule(activityModule)
            .applicationComponent(applicationComponent)
            .build()
    }
}
