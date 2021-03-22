package com.orcchg.direcall.github_repo.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.orcchg.direcall.github_repo.R
import com.orcchg.direcall.github_repo.databinding.FragmentGithubRepoListBinding
import com.orcchg.direcall.github_repo.di.DaggerGithubRepoFragmentComponent
import com.orcchg.direcall.github_repo.di.GithubRepoModule
import com.orcchg.direcall.github_repo.presentation.adapter.GithubRepoListAdapter
import com.orcchg.direcall.github_repo.presentation.viewmodel.GithubRepoViewModel
import com.orcchg.direcall.github_repo.presentation.viewmodel.GithubRepoViewModelFactory
import com.orcchg.yandexcontest.androidutil.argument
import com.orcchg.yandexcontest.androidutil.observe
import com.orcchg.yandexcontest.androidutil.viewBindings
import com.orcchg.yandexcontest.coredi.getFeature
import com.orcchg.yandexcontest.coreui.BaseFragment
import javax.inject.Inject

class GithubRepoFragment : BaseFragment(R.layout.fragment_github_repo_list) {

    @Inject lateinit var factory: GithubRepoViewModelFactory

    private val adapter = GithubRepoListAdapter()
    private val binding by viewBindings(FragmentGithubRepoListBinding::bind)
    private val login by argument<String>("login")
    private val viewModel by viewModels<GithubRepoViewModel> { factory }

    override fun onAttach(context: Context) {
        DaggerGithubRepoFragmentComponent.factory()
            .create(
                module = GithubRepoModule(login),
                networkApi = api.getFeature(),
                schedulerApi = api.getFeature()
            )
            .inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvRepos.adapter = adapter

        observe(viewModel.repos, adapter::update)
    }
}
