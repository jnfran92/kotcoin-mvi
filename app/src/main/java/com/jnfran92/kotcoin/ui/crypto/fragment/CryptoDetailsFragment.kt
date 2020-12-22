package com.jnfran92.kotcoin.ui.crypto.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.jnfran92.kotcoin.databinding.FragmentCryptoDetailsBinding
import com.jnfran92.kotcoin.databinding.FragmentCryptoListBinding
import com.jnfran92.kotcoin.presentation.crypto.CryptoDetailsViewModel
import com.jnfran92.kotcoin.presentation.crypto.CryptoListViewModel
import com.jnfran92.kotcoin.presentation.crypto.dataflow.intent.CryptoDetailsIntent
import com.jnfran92.kotcoin.presentation.crypto.dataflow.uistate.CryptoDetailsUIState
import com.jnfran92.kotcoin.presentation.crypto.dataflow.uistate.CryptoListUIState
import com.jnfran92.kotcoin.presentation.crypto.model.UICrypto
import com.jnfran92.kotcoin.ui.crypto.adapter.CryptoListAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

/**
 * Fragment for displaying Crypto List
 */
@AndroidEntryPoint
class CryptoDetailsFragment : Fragment() {


    /**
     * view binding
     */
    private lateinit var binding: FragmentCryptoDetailsBinding

    /**
     * view model
     */
    private val viewModel: CryptoDetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        Timber.d("onCreateView: ")
        this.binding = FragmentCryptoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        this.initViewElements()
        this.initViewModel()
    }

//    private fun initViewElements(){
//        this.binding.rvCryptoFragmentCryptoList.adapter = this.cryptoListAdapter
//        this.binding.rvCryptoFragmentCryptoList.layoutManager = this.cryptoLayoutManager
//    }

    private fun initViewModel() {
        Timber.d("initViewModel")
        this.viewModel.tx.observe(viewLifecycleOwner, Observer(::render))
        this.viewModel.rx(CryptoDetailsIntent.GetCryptoDetailsIntent(1))
    }


    private fun render(uiState: CryptoDetailsUIState){
        Timber.d("render: ")
        when(uiState){
            CryptoDetailsUIState.ShowDefaultView -> {
                binding.tvCryptoDetailsFragmentName.text = "-"
                binding.tvCryptoDetailsFragmentLastUpdated.text = "-"
                binding.tvCryptoDetailsFragmentMarketCap.text = "-"
                binding.tvCryptoDetailsFragmentPrice.text = "-"
                binding.tvCryptoDetailsFragmentSlug.text = "-"
                binding.tvCryptoDetailsFragmentSymbol.text = "-"
            }
            CryptoDetailsUIState.ShowLoadingView -> {
                binding.pbCryptoFragmentLoading.pbViewLoadingLoading.visibility = View.VISIBLE
            }
            CryptoDetailsUIState.ShowErrorRetryView -> {
                binding.pbCryptoFragmentLoading.pbViewLoadingLoading.visibility = View.GONE
            }
            is CryptoDetailsUIState.ShowDataView -> {
                binding.pbCryptoFragmentLoading.pbViewLoadingLoading.visibility = View.GONE
                binding.tvCryptoDetailsFragmentName.text = uiState.data.name
                binding.tvCryptoDetailsFragmentLastUpdated.text = uiState.data.lastUpdated
                binding.tvCryptoDetailsFragmentMarketCap.text = uiState.data.marketCap.toString()
                binding.tvCryptoDetailsFragmentPrice.text = uiState.data.price.toString()
                binding.tvCryptoDetailsFragmentSlug.text = uiState.data.slug
                binding.tvCryptoDetailsFragmentSymbol.text = uiState.data.symbol
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume: ")
    }
}