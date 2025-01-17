package com.orcchg.yandexcontest.main.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import com.jakewharton.rxbinding3.view.clicks
import com.orcchg.yandexcontest.androidutil.argument
import com.orcchg.yandexcontest.androidutil.clickThrottle
import com.orcchg.yandexcontest.androidutil.detachableAdapter
import com.orcchg.yandexcontest.androidutil.observe
import com.orcchg.yandexcontest.androidutil.viewBindings
import com.orcchg.yandexcontest.coredi.getFeature
import com.orcchg.yandexcontest.coremodel.StockSelection
import com.orcchg.yandexcontest.coreui.BaseFragment
import com.orcchg.yandexcontest.main.di.DaggerStockListFragmentComponent
import com.orcchg.yandexcontest.main.ui.databinding.MainStockListFragmentBinding
import com.orcchg.yandexcontest.main.viewmodel.StockListViewModel
import com.orcchg.yandexcontest.main.viewmodel.StockListViewModelFactory
import com.orcchg.yandexcontest.main.viewmodel.StockPagesViewModel
import com.orcchg.yandexcontest.stocklist.ui.adapter.StockListAdapter
import com.orcchg.yandexcontest.util.onFailure
import com.orcchg.yandexcontest.util.onLoading
import com.orcchg.yandexcontest.util.onSuccess
import javax.inject.Inject

internal class StockListFragment : BaseFragment(R.layout.main_stock_list_fragment) {

    @Inject lateinit var stockListAdapter: StockListAdapter
    @Inject lateinit var factory: StockListViewModelFactory
    private val stockSelection by argument<StockSelection>(BUNDLE_KEY_STOCK_SELECTION)
    private val binding by viewBindings(MainStockListFragmentBinding::bind)
    private val viewModel by viewModels<StockListViewModel> { factory }
    private val pagesViewModel by activityViewModels<StockPagesViewModel>()

    override fun onAttach(context: Context) {
        DaggerStockListFragmentComponent.factory()
            .create(
                stockSelection = stockSelection,
                featureApi = api.getFeature()
            )
            .inject(this)
        super.onAttach(context)
    }

    @Suppress("AutoDispose", "CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stockListAdapter.itemClickListener = {
            findNavController().navigate(MainNavSubgraphDirections.navActionOpenStockDetailsFragment(it.ticker))
        }
        stockListAdapter.favIconClickListener = {
            viewModel.setIssuerFavourite(it.ticker, it.isFavourite)
        }
        binding.rvItems.detachableAdapter = stockListAdapter
        binding.btnError.clicks().clickThrottle().subscribe { viewModel.retryLoadStocks() }
        binding.swipeRefresh.refreshes().clickThrottle().subscribe { viewModel.retryLoadStocks() }

        observe(viewModel.stocks) {
            binding.swipeRefresh.isRefreshing = false
            it.onLoading { showLoading(true) }
            it.onSuccess { data ->
                stockListAdapter.update(data)
                showError(false)
                showLoading(false)
            }
            it.onFailure { showError(true) }
        }
        observe(pagesViewModel.stockSelection, viewModel::setCurrentPage)
    }

    private fun showLoading(isShow: Boolean) {
        binding.pbLoading.isInvisible = !isShow
        binding.tvError.isVisible = false
        binding.btnError.isVisible = false
    }

    private fun showError(isShow: Boolean) {
        binding.pbLoading.isInvisible = isShow
        binding.tvError.isVisible = isShow
        binding.btnError.isVisible = isShow
    }

    companion object {
        private const val BUNDLE_KEY_STOCK_SELECTION = "bundle_key_stock_selection"

        @JvmStatic
        fun newInstance(stockSelection: StockSelection): StockListFragment =
            StockListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(BUNDLE_KEY_STOCK_SELECTION, stockSelection)
                }
            }
    }
}
