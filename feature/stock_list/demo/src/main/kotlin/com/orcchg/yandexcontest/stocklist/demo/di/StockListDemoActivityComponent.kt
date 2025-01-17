package com.orcchg.yandexcontest.stocklist.demo.di

import com.orcchg.yandexcontest.coremodel.StockSelection
import com.orcchg.yandexcontest.stock_list.ui.di.FakeStockListVoConverterModule
import com.orcchg.yandexcontest.stocklist.api.StockListFeatureApi
import com.orcchg.yandexcontest.stocklist.demo.ui.StockListDemoActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        FakeStockListVoConverterModule::class
    ],
    dependencies = [
        StockListFeatureApi::class
    ]
)
internal interface StockListDemoActivityComponent {

    fun inject(target: StockListDemoActivity)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance stockSelection: StockSelection,
            featureApi: StockListFeatureApi
        ): StockListDemoActivityComponent
    }
}
