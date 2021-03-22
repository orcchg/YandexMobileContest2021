package com.orcchg.yandexcontest.stocklist

import com.orcchg.yandexcontest.stocklist.api.StockListInteractor
import com.orcchg.yandexcontest.stocklist.api.model.Issuer
import com.orcchg.yandexcontest.stocklist.api.model.Quote
import com.orcchg.yandexcontest.stocklist.api.model.Stock
import com.orcchg.yandexcontest.stocklist.domain.usecase.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class StockListInteractorImpl @Inject constructor(
    private val findIssuersByQueryUseCase: FindIssuersByQueryUseCase,
    private val getDefaultIssuersUseCase: GetDefaultIssuersUseCase,
    private val getFavouriteIssuersUseCase: GetFavouriteIssuersUseCase,
    private val getQuoteByTickerUseCase: GetQuoteByTickerUseCase,
    private val setIssuerFavouriteUseCase: SetIssuerFavouriteUseCase
) : StockListInteractor {

    override fun issuers(): Single<List<Issuer>> = getDefaultIssuersUseCase.source()

    override fun favouriteIssuers(): Single<List<Issuer>> = getFavouriteIssuersUseCase.source()

    override fun setIssuerFavourite(ticker: String, isFavourite: Boolean): Completable =
        setIssuerFavouriteUseCase.source {
            SetIssuerFavouriteUseCase.PARAM_TICKER of ticker
            SetIssuerFavouriteUseCase.PARAM_IS_FAVOURITE of isFavourite
        }

    override fun quote(ticker: String): Single<Quote> =
        getQuoteByTickerUseCase.source { GetQuoteByTickerUseCase.PARAM_TICKER of ticker }

    override fun stocks(): Single<List<Stock>> =
        getStocks(issuersSource = issuers())

    override fun favouriteStocks(): Single<List<Stock>> =
        getStocks(issuersSource = favouriteIssuers())

    override fun findStocks(querySource: Observable<String>): Observable<List<Stock>> =
        querySource.switchMap { query ->
            findIssuersByQueryUseCase.source { FindIssuersByQueryUseCase.PARAM_QUERY of query }
                .let(::getStocks)
                .toObservable()
        }

    private fun getStocks(issuersSource: Single<List<Issuer>>): Single<List<Stock>> =
        issuersSource
            .flatMapObservable {
                Observable.fromIterable(it)
                    .flatMapSingle { issuer ->
                        quote(issuer.ticker)
                            .map { quote ->
                                Stock(
                                    id = issuer.ticker,
                                    name = issuer.name,
                                    price = quote.currentPrice,
                                    priceDailyChange = quote.currentPrice - quote.prevClosePrice,
                                    logoUrl = issuer.logoUrl,
                                    isFavourite = issuer.isFavourite
                                )
                            }
                    }
            }
            .toList()
}
