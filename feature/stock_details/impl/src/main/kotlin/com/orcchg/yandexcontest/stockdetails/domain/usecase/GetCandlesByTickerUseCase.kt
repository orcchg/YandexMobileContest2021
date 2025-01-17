package com.orcchg.yandexcontest.stockdetails.domain.usecase

import com.orcchg.yandexcontest.base.Params
import com.orcchg.yandexcontest.base.usecase.SingleUseCase
import com.orcchg.yandexcontest.core.schedulers.api.SchedulersFactory
import com.orcchg.yandexcontest.stockdetails.api.model.Candle
import com.orcchg.yandexcontest.stockdetails.data.api.StockDetailsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetCandlesByTickerUseCase @Inject constructor(
    private val repository: StockDetailsRepository,
    schedulersFactory: SchedulersFactory
) : SingleUseCase<List<Candle>>(schedulersFactory) {

    override fun sourceImpl(params: Params): Single<List<Candle>> {
        val ticker = params.require<String>(PARAM_TICKER)
        val resolution = params.require<Candle.Resolution>(PARAM_RESOLUTION)
        val fromTs = params.require<Long>(PARAM_TS_FROM)
        val toTs = params.require<Long>(PARAM_TS_TO)
        return repository.candles(
            ticker = ticker,
            resolution = resolution,
            fromTs = fromTs,
            toTs = toTs
        )
    }

    companion object {
        const val PARAM_TICKER = "ticker"
        const val PARAM_RESOLUTION = "resolution"
        const val PARAM_TS_FROM = "fromTs"
        const val PARAM_TS_TO = "toTs"
    }
}
