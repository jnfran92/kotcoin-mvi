package com.jnfran92.kotcoin.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.jnfran92.kotcoin.R
import com.jnfran92.kotcoin.controller.CryptoController
import com.jnfran92.kotcoin.view.ViewListener
import com.jnfran92.kotcoin.view.activity.CryptoActivity
import com.jnfran92.kotcoin.view.adapter.CryptoListAdapter
import com.jnfran92.model.data.crypto.Crypto
import kotlinx.android.synthetic.main.frament_crypto_list.*
import kotlinx.android.synthetic.main.view_loading.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Fragment for display
 */
class CryptoListFragment : Fragment(), ViewListener<Crypto> {

    @Inject
    lateinit var cryptoController: CryptoController

    @Inject
    lateinit var cryptoListAdapter: CryptoListAdapter

    @Inject
    lateinit var cryptoLayoutManager: RecyclerView.LayoutManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frament_crypto_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initInjection()
        this.initViewElements()

        this.displayCryptoList()
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy")
        this.cryptoController.dispose()
    }

    fun displayCryptoList(){
        this.cryptoListAdapter.setData(ArrayList())
        this.cryptoController.displayCryptoList()
    }

    private fun initInjection(){
        val cryptoComponent = (activity as CryptoActivity).getCryptoComponent()
        cryptoComponent.inject(this)
    }

    private fun initViewElements(){
        this.cryptoController.viewListener = this
        rv_cryptoFragment_cryptoList.adapter = this.cryptoListAdapter
        rv_cryptoFragment_cryptoList.layoutManager = this.cryptoLayoutManager
    }

    private fun showToastMessage(message: String){
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        Timber.d("showLoading")
        pb_viewLoading_loading.visibility = View.VISIBLE
        pb_viewLoading_loading.isIndeterminate = true
    }

    override fun hideLoading() {
        Timber.d("hideLoading")
        pb_viewLoading_loading.visibility = View.GONE
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
    }
}