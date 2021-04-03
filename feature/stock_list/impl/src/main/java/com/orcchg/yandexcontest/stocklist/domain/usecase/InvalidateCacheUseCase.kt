package com.orcchg.yandexcontest.stocklist.domain.usecase

import com.orcchg.yandexcontest.base.Params
import com.orcchg.yandexcontest.base.usecase.CompletableUseCase
import com.orcchg.yandexcontest.scheduler.api.SchedulersFactory
import com.orcchg.yandexcontest.stocklist.domain.RealTimeStocksRepository
import com.orcchg.yandexcontest.stocklist.domain.StockListRepository
import io.reactivex.Completable
import javax.inject.Inject

class InvalidateCacheUseCase @Inject constructor(
    private val repository: StockListRepository,
    private val realTimeStocksRepository: RealTimeStocksRepository,
    schedulersFactory: SchedulersFactory
) : CompletableUseCase(schedulersFactory) {

    override fun sourceImpl(params: Params): Completable =
        repository.invalidateCache()
            .andThen(realTimeStocksRepository.invalidate())
}
