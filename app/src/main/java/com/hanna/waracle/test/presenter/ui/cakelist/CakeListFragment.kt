package com.hanna.waracle.test.presenter.ui.cakelist

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hanna.waracle.test.R
import com.hanna.waracle.test.data.api.Resource
import com.hanna.waracle.test.databinding.FragmentCakeListBinding
import com.hanna.waracle.test.domain.models.entities.CakeItem
import com.hanna.waracle.test.presenter.alerts.showErrorAlert
import com.hanna.waracle.test.presenter.alerts.showSimpleAlert
import com.hanna.waracle.test.presenter.ui.viewmodels.CakeListViewModel
import com.hanna.waracle.test.utils.ui.viewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CakeListFragment : Fragment(R.layout.fragment_cake_list) {

    private val viewModel by viewModels<CakeListViewModel>()

    private val adapter = CakesRecyclerViewAdapter(::onItemClick)

    private val binding: FragmentCakeListBinding by viewBinding(FragmentCakeListBinding::bind)

    private fun onItemClick(cakeItem: CakeItem) {
        showSimpleAlert(parentFragmentManager, cakeItem.description)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cakesRecyclerView.addItemDecoration(
            DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        )
        binding.cakesRecyclerView.adapter = adapter
        binding.refreshComponent.setOnRefreshListener {
            binding.refreshComponent.isRefreshing = false
            viewModel.fetchCakes(true)
        }
        viewModel.cakesSummary.observe(viewLifecycleOwner) {
            binding.loadingPage.root.isVisible = it.status == Resource.Status.LOADING
            binding.swipeInstruction.isVisible = it.data.orEmpty().isNotEmpty()
            adapter.submitList(it.data.orEmpty())
            when (it.status) {
                Resource.Status.ERROR -> {
                    showErrorAlert(requireContext(), it.message.orEmpty()) {
                        viewModel.fetchCakes()
                    }
                }
                else -> {}
            }
        }

    }
}