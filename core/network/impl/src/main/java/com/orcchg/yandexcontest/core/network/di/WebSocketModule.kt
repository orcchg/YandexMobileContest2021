package com.orcchg.yandexcontest.core.network.di

import com.orcchg.yandexcontest.coredi.PublishedNoReasonableAlternatives
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.messageadapter.moshi.MoshiMessageAdapter
import com.tinder.scarlet.streamadapter.rxjava2.RxJava2StreamAdapterFactory
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient

@Module(includes = [CloudModule::class])
@PublishedNoReasonableAlternatives
object WebSocketModule {

    @Provides
    @Reusable
    fun scarlet(client: OkHttpClient): Scarlet =
        Scarlet.Builder()
            .webSocketFactory(client.newWebSocketFactory("wss://ws.finnhub.io?token=$API_KEY"))
            .addStreamAdapterFactory(RxJava2StreamAdapterFactory())
            .addMessageAdapterFactory(MoshiMessageAdapter.Factory())
            .build()
}
