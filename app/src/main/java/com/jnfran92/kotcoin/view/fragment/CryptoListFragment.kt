package com.jnfran92.kotcoin.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jnfran92.kotcoin.R
import com.jnfran92.kotcoin.controller.CryptoController
import com.jnfran92.kotcoin.di.PerActivity
import com.jnfran92.kotcoin.view.ViewListener
import com.jnfran92.kotcoin.view.activity.CryptoActivity
import com.jnfran92.kotcoin.view.adapter.CryptoListAdapter
import com.jnfran92.model.data.crypto.Crypto
import kotlinx.android.synthetic.main.frament_crypto_list.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Fragment for display
 */
class CryptoListFragment : Fragment(), ViewListener<Crypto> {

    @Inject @PerActivity
    lateinit var cryptoController: CryptoController

    @Inject @PerActivity
    lateinit var cryptoListAdapter: CryptoListAdapter

    @Inject @PerActivity
    lateinit var cryptoLayoutManager: RecyclerView.LayoutManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frament_crypto_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initInjection()
        this.initViewElements()

        this.cryptoController.displayCryptoList()
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy")
        this.cryptoController.dispose()
    }

    private fun initInjection(){
        val cryptoComponent = (activity as CryptoActivity).cryptoComponent
        cryptoComponent.inject(this)
    }

    private fun initViewElements(){
        this.cryptoController.viewListener = this

        this.cryptoListAdapter.setData(ArrayList())

        rv_cryptoFragment_cryptoList.adapter = this.cryptoListAdapter
        rv_cryptoFragment_cryptoList.layoutManager = LinearLayoutManager(context)
    }

    private fun showToastMessage(message: String){
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        Timber.d("showLoading")

    }

    override fun hideLoading() {
        Timber.d("hideLoading")

    }

    override fun showErrorMessage(message: String) {
        Timber.d("showErrorMessage: %s", message)
        this.showToastMessage(message)
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
        this.cryptoListAdapter.setData(t as ArrayList<Crypto>)

        for (i in t){
            println("-----")
            println(i.name + " " + i.symbol + "   USD $" + i.quoteEntity.usd.price)
        }
    }
}