package com.jnfran92.kotcoin.ui.crypto.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.jnfran92.kotcoin.databinding.FragmentCryptoDetailsBinding
import com.jnfran92.kotcoin.presentation.crypto.CryptoDetailsViewModel
import com.jnfran92.kotcoin.presentation.crypto.dataflow.intent.CryptoDetailsIntent
import com.jnfran92.kotcoin.presentation.crypto.dataflow.uistate.CryptoDetailsUIState
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

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

    /**
     * input args
     */
    private val args: CryptoDetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        Timber.d("onCreateView: ")
        this.binding = FragmentCryptoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initViewElements()
        this.initViewModel()
    }

    private fun initViewElements(){
        Timber.d("initViewElements: ")
        (requireActivity() as AppCompatActivity).supportActionBar?.title = args.CryptoItem.name
    }

    private fun initViewModel() {
        Timber.d("initViewModel: get crypto details by id: ${args.CryptoItem.cryptoId}")
        this.viewModel.tx.observe(viewLifecycleOwner, Observer(::render))
        this.viewModel.rx(CryptoDetailsIntent.GetCryptoDetailsIntent(args.CryptoItem.cryptoId))
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
                binding.pbLoading.pbViewLoadingLoading.visibility = View.VISIBLE
            }
            is CryptoDetailsUIState.ShowErrorRetryView -> {
                Timber.d("render: uiState error ${uiState.t}")
                binding.pbLoading.pbViewLoadingLoading.visibility = View.GONE
                binding.lyDataContainer.visibility = View.GONE
                binding.lyErrorRetryContainer.container.visibility = View.VISIBLE

                binding.lyErrorRetryContainer.btErrorRetryViewGenericRetry.setOnClickListener {
                    Timber.d("render: onClickListener")
                    viewModel.rx(CryptoDetailsIntent.GetCryptoDetailsIntent(args.CryptoItem.cryptoId))
                }
            }
            is CryptoDetailsUIState.ShowDataView -> {
                binding.pbLoading.pbViewLoadingLoading.visibility = View.GONE

                binding.lyDataContainer.visibility = View.VISIBLE
                binding.lyErrorRetryContainer.container.visibility = View.GONE

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

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy: ")
    }
}