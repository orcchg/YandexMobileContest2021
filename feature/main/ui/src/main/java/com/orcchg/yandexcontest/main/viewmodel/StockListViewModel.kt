package com.orcchg.yandexcontest.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.orcchg.yandexcontest.coremodel.StockSelection
import com.orcchg.yandexcontest.coreui.AutoDisposeViewModel
import com.orcchg.yandexcontest.stocklist.api.StockListInteractor
import com.orcchg.yandexcontest.stocklist.convert.StockVoConverter
import com.orcchg.yandexcontest.stocklist.model.StockVO
import com.orcchg.yandexcontest.util.DataState
import com.uber.autodispose.autoDispose
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

internal class StockListViewModel @Inject constructor(
    private val interactor: StockListInteractor,
    private val stockSelection: StockSelection,
    private val stockVoConverter: StockVoConverter
) : AutoDisposeViewModel() {

    private var currentPageStockSelection: StockSelection = stockSelection

    private val _stocks by lazy(LazyThreadSafetyMode.NONE) {
        val data = MutableLiveData<DataState<List<StockVO>>>()
        loadStocks(data)
        data
    }
    internal val stocks: LiveData<DataState<List<StockVO>>> by lazy(LazyThreadSafetyMode.NONE) { _stocks }

    init {
        when (stockSelection) {
            StockSelection.ALL ->
                interactor.favouriteIssuersChanged
                    // update items on ALL page only when it's not currently selected
                    .filter { currentPageStockSelection != StockSelection.ALL }
                    .observeOn(AndroidSchedulers.mainThread())
                    .autoDispose(this)
                    .subscribe({ issuer ->
                        _stocks.value?.getOrNull()?.let { stocks ->
                            stocks.indexOfFirst { it.ticker == issuer.ticker }
                                .takeIf { it != -1 }
                                ?.let {
                                    val list = stocks.toMutableList()
                                    list[it] = stocks[it].copy(isFavourite = issuer.isFavourite)
                                    _stocks.value = DataState.success(list)
                                }
                        }
                    }, Timber::e)

            StockSelection.FAVOURITE ->
                interactor.favouriteIssuersChanged
                    .observeOn(AndroidSchedulers.mainThread())
                    .autoDispose(this)
                    .subscribe({ loadStocks(_stocks) }, Timber::e)

            else -> throw IllegalStateException("Unsupported stock selection")
        }
    }

    fun retryLoadStocks() {
        interactor.invalidateCache(stockSelection)
            .doOnComplete { loadStocks(_stocks) }
            .autoDispose(this)
            .subscribe({}, Timber::e)
    }

    fun setCurrentPage(stockSelection: StockSelection) {
        currentPageStockSelection = stockSelection
    }

    fun setIssuerFavourite(ticker: String, isFavourite: Boolean) {
        interactor.setIssuerFavourite(ticker, isFavourite)
            .autoDispose(this)
            .subscribe({}, Timber::e)
    }

    private fun loadStocks(data: MutableLiveData<DataState<List<StockVO>>>) {
        val source = when (stockSelection) {
            StockSelection.ALL -> interactor.stocks()
            StockSelection.FAVOURITE -> interactor.favouriteStocks()
            else -> throw IllegalStateException("Unsupported stock selection")
        }
        source.doOnSubscribe { data.value = DataState.loading() }
            .map(stockVoConverter::convertList)
            .autoDispose(this)
            .subscribe({
                data.value = DataState.success(it)
            }, {
                Timber.e(it)
                data.value = DataState.failure(it)
            })
    }
}
