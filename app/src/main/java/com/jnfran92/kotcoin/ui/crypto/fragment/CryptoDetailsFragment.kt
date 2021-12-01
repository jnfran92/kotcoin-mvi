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
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.jnfran92.kotcoin.R
import com.jnfran92.kotcoin.databinding.FragmentCryptoDetailsBinding
import com.jnfran92.kotcoin.presentation.crypto.CryptoDetailsViewModel
import com.jnfran92.kotcoin.presentation.crypto.dataflow.intent.CryptoDetailsIntent
import com.jnfran92.kotcoin.presentation.crypto.dataflow.uistate.CryptoDetailsUIState
import com.jnfran92.kotcoin.presentation.crypto.model.UIPrice
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("onCreateView: ")
        this.binding = FragmentCryptoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initViewElements()
        this.initViewModel()
    }

    private fun initViewElements() {
        Timber.d("initViewElements: ")
        (requireActivity() as AppCompatActivity).supportActionBar?.title = args.cryptoItem.name
    }

    private fun initViewModel() {
        Timber.d("initViewModel: get crypto details by id: ${args.cryptoItem.id}")
        this.viewModel.tx.observe(viewLifecycleOwner, Observer(::render))
        this.viewModel.rx(CryptoDetailsIntent.GetCryptoDetailsIntent(args.cryptoItem.id))
    }

    private fun render(uiState: CryptoDetailsUIState) {
        Timber.d("render: ")
        when (uiState) {
            CryptoDetailsUIState.ShowDefaultView -> {
            }
            CryptoDetailsUIState.ShowLoadingView -> {
                binding.loadingView.container.visibility = View.VISIBLE
                binding.lyDataContainer.visibility = View.GONE
            }
            is CryptoDetailsUIState.ShowErrorRetryView -> {
                Timber.d("render: uiState error ${uiState.t}")
                binding.loadingView.container.visibility = View.GONE
                binding.lyDataContainer.visibility = View.GONE
                binding.lyErrorRetryContainer.container.visibility = View.VISIBLE

                binding.lyErrorRetryContainer.btErrorRetryViewGenericRetry.setOnClickListener {
                    Timber.d("render: onClickListener")
                    viewModel.rx(CryptoDetailsIntent.GetCryptoDetailsIntent(args.cryptoItem.id))
                }
            }
            is CryptoDetailsUIState.ShowDataView -> {
                Timber.d("render: show data details: ${uiState.data}")
                Timber.d("render: show data details price list: ${uiState.data.usdPrices}")
                setHistoricData(uiState.data.usdPrices)

                binding.loadingView.container.visibility = View.GONE

                binding.lyDataContainer.visibility = View.VISIBLE
                binding.lyErrorRetryContainer.container.visibility = View.GONE

                binding.name.label.text = "Name"
                binding.name.textContent.text = uiState.data.name
                binding.name.icon.setImageDrawable(requireContext().getDrawable(R.drawable.ic_baseline_info_24))

                binding.slug.label.text = "Slug"
                binding.slug.textContent.text = uiState.data.slug
                binding.slug.icon.setImageDrawable(requireContext().getDrawable(R.drawable.ic_baseline_image_aspect_ratio_24))

                binding.symbol.label.text = "Symbol"
                binding.symbol.textContent.text = uiState.data.symbol
                binding.symbol.icon.setImageDrawable(requireContext().getDrawable(R.drawable.ic_baseline_insert_emoticon_24))

                binding.price.label.text = "Last Price[USD]"
                binding.price.textContent.text = uiState.data.usdPrices.last().price.toString()
                binding.price.icon.setImageDrawable(requireContext().getDrawable(R.drawable.ic_baseline_attach_money_24))
            }
        }
    }


    private fun setHistoricData(historicData: List<UIPrice>) {
        val entries = historicData.mapIndexed { index, uiPrice ->
            Entry(index.toFloat(), uiPrice.price.toFloat())
        }

        val dataSet = LineDataSet(entries, null)
        dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        dataSet.setDrawFilled(true)
        dataSet.setDrawCircles(false)
        dataSet.lineWidth = 3.0f
        dataSet.valueTextSize = 0.0f
        dataSet.setDrawValues(false)

        val lineData = LineData(dataSet)

        binding.chart.data = lineData
        binding.chart.legend.isEnabled = false
        binding.chart.description = null

        binding.chart.xAxis.setDrawGridLines(false)
        binding.chart.axisLeft.setDrawGridLines(false)
        binding.chart.axisRight.setDrawGridLines(false)

        binding.chart.xAxis.setDrawAxisLine(false)
        binding.chart.axisLeft.setDrawAxisLine(false)
        binding.chart.axisRight.setDrawAxisLine(false)
        binding.chart.axisRight.isEnabled = false

        binding.chart.axisRight.textColor = resources.getColor(R.color.white, null)
        binding.chart.axisLeft.textColor = resources.getColor(R.color.white, null)
        binding.chart.xAxis.textColor = resources.getColor(R.color.white, null)
        binding.chart.legend.textColor = resources.getColor(R.color.white, null)

        val formatter = SimpleDateFormat("yyyy-mm-dd", Locale.US)

        binding.chart.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                val date = formatter.parse(historicData[value.toInt()].lastUpdated)
                return formatter.format(date ?: "")
            }
        }
        binding.chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        binding.chart.xAxis.labelRotationAngle = 45.0f
        binding.chart.xAxis.isGranularityEnabled = true
        binding.chart.xAxis.granularity = 7f
        binding.chart.extraBottomOffset = 40f
        binding.chart.setTouchEnabled(true)
        binding.chart.setDrawBorders(false)
        binding.chart.setDrawGridBackground(false)
        binding.chart.invalidate()
    }
}