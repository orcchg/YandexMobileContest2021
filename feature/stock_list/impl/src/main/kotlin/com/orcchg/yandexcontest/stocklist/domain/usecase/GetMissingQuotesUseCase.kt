package com.orcchg.yandexcontest.stocklist.domain.usecase

import com.orcchg.yandexcontest.base.Params
import com.orcchg.yandexcontest.base.usecase.CompletableUseCase
import com.orcchg.yandexcontest.core.schedulers.api.SchedulersFactory
import com.orcchg.yandexcontest.stocklist.data.api.StockListRepository
import io.reactivex.Completable
import javax.inject.Inject

class GetMissingQuotesUseCase @Inject constructor(
    private val repository: StockListRepository,
    schedulersFactory: SchedulersFactory
) : CompletableUseCase(schedulersFactory) {

    override fun sourceImpl(params: Params): Completable =
        repository.getMissingQuotes()
}
