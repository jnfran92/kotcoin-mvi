package com.jnfran92.kotcoin.view.activity

import android.os.Bundle
import com.jnfran92.kotcoin.R
import com.jnfran92.kotcoin.controller.CryptoController
import com.jnfran92.kotcoin.di.component.CryptoComponent
import com.jnfran92.kotcoin.di.component.DaggerCryptoComponent
import com.jnfran92.kotcoin.view.ViewListener
import com.jnfran92.model.data.crypto.Crypto

class MainActivity : BaseActivity(), ViewListener<Crypto> {

    private lateinit var cryptoComponent: CryptoComponent
    private lateinit var cryptosController: CryptoController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCryptoComponent()

//
//        val cryptoModel = cryptoComponent.cryptoModel()
//
//
//
//        cryptoModel.getCryptoList()
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribeBy(
//                onComplete = { println("onComplete")},
//                onError = { println("onComplete")},
//                onNext = {
//                    run{
//                        println("onNext")
//                        for (i in it){
//                            println("-----")
//                            println(i.name + " " + i.symbol + "   USD $" + i.quoteEntity.usd.price)
//                        }
//                    }
//                }
//            )

    }

    private fun initCryptoComponent(){
        this.cryptoComponent = DaggerCryptoComponent
            .builder()
            .activityModule(activityModule)
            .applicationComponent(applicationComponent)
            .build()
    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showErrorMessage(message: String) {

    }

    override fun showRetry() {

    }

    override fun hideRetry() {

    }

    override fun showData(t: Crypto) {

    }

    override fun showDataList(t: List<Crypto>) {

    }


}
