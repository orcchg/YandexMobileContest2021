package com.orcchg.yandexcontest.stockdetails.main.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.orcchg.yandexcontest.androidutil.observe
import com.orcchg.yandexcontest.androidutil.showToast
import com.orcchg.yandexcontest.androidutil.viewBindings
import com.orcchg.yandexcontest.coremodel.formatPriceChange
import com.orcchg.yandexcontest.coreui.BaseFragment
import com.orcchg.yandexcontest.design.rx_ext.checkedChanges
import com.orcchg.yandexcontest.stockdetails.main.R
import com.orcchg.yandexcontest.stockdetails.main.databinding.StockDetailsChartFragmentBinding
import com.orcchg.yandexcontest.stockdetails.main.viewmodel.StockDetailsChartViewModel
import com.orcchg.yandexcontest.stockdetails.main.viewmodel.StockDetailsChartViewModelFactory
import com.orcchg.yandexcontest.stockdetails.main.viewmodel.StockDetailsMainViewModel
import com.orcchg.yandexcontest.util.onFailure
import com.orcchg.yandexcontest.util.onLoading
import com.orcchg.yandexcontest.util.onSuccess
import javax.inject.Inject

internal class StockDetailsChartFragment : BaseFragment(R.layout.stock_details_chart_fragment) {

    @Inject lateinit var factory: StockDetailsChartViewModelFactory
    private val binding by viewBindings(StockDetailsChartFragmentBinding::bind)
    private val viewModel by viewModels<StockDetailsChartViewModel> { factory }
    private val sharedViewModel by activityViewModels<StockDetailsMainViewModel>()

    @Suppress("AutoDispose", "CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            radioGroup.checkedChanges().skipInitialValue().subscribe { (id, isChecked) ->
                when (id) {
                    btnPlotDay.id -> context?.showToast("Daily: $isChecked")
                    btnPlotWeek.id -> context?.showToast("Weekly: $isChecked")
                    btnPlotMonth.id -> context?.showToast("Monthly: $isChecked")
                    btnPlotSixMonths.id -> context?.showToast("Half-year: $isChecked")
                    btnPlotYear.id -> context?.showToast("Annual: $isChecked")
                    btnPlotAll.id -> context?.showToast("All history: $isChecked")
                }
            }
        }

        observe(sharedViewModel.quote) {
            it.onLoading { } // TODO: load
            it.onSuccess { quote ->
                binding.tvStockPrice.text = quote.currentPrice.toString()
                binding.tvStockPriceChange.text = formatPriceChange(quote.currentPrice, quote.priceDayChange)
            }
            it.onFailure { } // TODO: fail
        }
        observe(viewModel.candles) // TODO: add candles on plot
    }

    companion object {
        @JvmStatic
        fun newInstance(): StockDetailsChartFragment = StockDetailsChartFragment()
    }
}
