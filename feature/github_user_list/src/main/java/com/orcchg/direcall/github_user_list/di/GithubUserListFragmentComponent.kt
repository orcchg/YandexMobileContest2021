package com.orcchg.direcall.github_user_list.di

import com.orcchg.direcall.github_user_list.presentation.ui.GithubUserListFragment
import com.orcchg.yandexcontest.core.network.api.NetworkApi
import com.orcchg.yandexcontest.scheduler.api.di.SchedulerApi
import dagger.Component

@Component(
    modules = [
        GithubUserListModule::class
    ],
    dependencies = [
        NetworkApi::class,
        SchedulerApi::class
    ]
)
interface GithubUserListFragmentComponent {

    @Component.Factory
    interface Factory {

        fun create(
            networkApi: NetworkApi,
            schedulerApi: SchedulerApi
        ): GithubUserListFragmentComponent
    }

    fun inject(target: GithubUserListFragment)
}
