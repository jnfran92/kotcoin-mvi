package com.jnfran92.kotcoin.ui.crypto.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.jnfran92.kotcoin.databinding.FragmentCryptoListBinding
import com.jnfran92.kotcoin.presentation.crypto.CryptoListViewModel
import com.jnfran92.kotcoin.presentation.crypto.dataflow.intent.CryptoListIntent
import com.jnfran92.kotcoin.presentation.crypto.dataflow.uistate.CryptoListUIState
import com.jnfran92.kotcoin.presentation.crypto.model.UICrypto
import com.jnfran92.kotcoin.ui.crypto.adapter.CryptoListAdapter
import com.jnfran92.kotcoin.ui.crypto.navigator.CryptoListNavigator
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

/**
 * Fragment for displaying Crypto List
 */
@AndroidEntryPoint
class CryptoListFragment : Fragment() {

    @Inject
    lateinit var cryptoListAdapter: CryptoListAdapter
    @Inject
    lateinit var navigator: CryptoListNavigator

    /**
     * view binding
     */
    private lateinit var binding: FragmentCryptoListBinding

    /**
     * view model
     */
    private val viewModel: CryptoListViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        Timber.d("onCreateView: ")
        this.binding = FragmentCryptoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initViewElements()
        this.initViewModel()
    }

    private fun initViewElements(){
        this.cryptoListAdapter.setListener{
            navigator.goToCryptoDetails(requireView(), it)
        }

        val orientation = requireContext().resources.configuration.orientation
        var layoutManager = GridLayoutManager(context, 1)
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutManager = GridLayoutManager(context, 2)
        }

        this.binding.rvCryptoFragmentCryptoList.adapter = this.cryptoListAdapter
        this.binding.rvCryptoFragmentCryptoList.layoutManager = layoutManager
    }

    private fun initViewModel() {
        Timber.d("initViewModel")
        this.viewModel.tx.observe(viewLifecycleOwner, Observer(::render))
    }


    private fun render(uiState: CryptoListUIState){
        Timber.d("render: $uiState")
        when(uiState){
            CryptoListUIState.ShowDefaultView -> {
                binding.lyDataContainer.visibility = View.VISIBLE
                this.cryptoListAdapter.setData(arrayListOf(
                    UICrypto(-1, "", "-", "", 0.0, 0.0, ""),
                    UICrypto(-1, "", "-", "", 0.0, 0.0, ""),
                    UICrypto(-1, "", "-", "", 0.0, 0.0, ""),
                    UICrypto(-1, "", "-", "", 0.0, 0.0, ""),
                    UICrypto(-1, "", "-", "", 0.0, 0.0, ""),
                    UICrypto(-1, "", "-", "", 0.0, 0.0, ""),
                ))
            }
            CryptoListUIState.ShowLoadingView -> {
                binding.pbLoading.pbViewLoadingLoading.visibility = View.VISIBLE
            }
            CryptoListUIState.ShowErrorRetryView -> {
                binding.pbLoading.pbViewLoadingLoading.visibility = View.GONE

                binding.lyDataContainer.visibility = View.GONE
                binding.lyErrorRetryContainer.container.visibility = View.VISIBLE

                binding.lyErrorRetryContainer.btErrorRetryViewGenericRetry.setOnClickListener {
                    Timber.d("render: onClickListener")
                    viewModel.rx(CryptoListIntent.GetCryptoListIntent)
                }
            }
            is CryptoListUIState.ShowDataView -> {
                binding.pbLoading.pbViewLoadingLoading.visibility = View.GONE

                binding.lyDataContainer.visibility = View.VISIBLE
                binding.lyErrorRetryContainer.container.visibility = View.GONE

                this.cryptoListAdapter.setData(uiState.data)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume: ")
    }
}