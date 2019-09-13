package com.jnfran92.kotcoin.view.activity

import android.os.Bundle
import com.jnfran92.kotcoin.R
import com.jnfran92.kotcoin.controller.CryptoController
import com.jnfran92.kotcoin.di.component.CryptoComponent
import com.jnfran92.kotcoin.di.component.DaggerCryptoComponent
import com.jnfran92.kotcoin.view.ViewListener
import com.jnfran92.model.data.crypto.Crypto
import timber.log.Timber
import javax.inject.Inject

class CryptoActivity : BaseActivity(), ViewListener<Crypto> {

    @Inject
    lateinit var cryptoController: CryptoController

    private lateinit var cryptoComponent: CryptoComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crypto)

        // Injection Stuff
        initCryptoComponent()
        initInjection()

        // Controller View
        setControllerViewListener()

        // Controller action
        cryptoController.displayCryptoList()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeController()
    }

    override fun setControllerViewListener() {
        this.cryptoController.viewListener = this
    }

    override fun disposeController() {
       this.cryptoController.dispose()
    }

    private fun initCryptoComponent(){
        this.cryptoComponent = DaggerCryptoComponent
            .builder()
            .activityModule(activityModule)
            .applicationComponent(applicationComponent)
            .build()
    }

    private fun initInjection(){
        this.cryptoComponent.inject(this)
    }

    override fun showLoading() {
        Timber.d("showLoading")

    }

    override fun hideLoading() {
        Timber.d("hideLoading")

    }

    override fun showErrorMessage(message: String) {
        Timber.d("showErrorMessage: %s", message)

    }

    override fun showRetry() {
        Timber.d("showRetry")

    }

    override fun hideRetry() {
        Timber.d("hideRetry")

    }

    override fun showData(t: Crypto) {
        Timber.d("showData")

    }

    override fun showDataList(t: List<Crypto>) {
        Timber.d("showDataList")

        for (i in t){
            println("-----")
            println(i.name + " " + i.symbol + "   USD $" + i.quoteEntity.usd.price)
        }
    }


}
