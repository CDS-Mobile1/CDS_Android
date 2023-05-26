package com.sopt.instagram.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sopt.instagram.R
import com.sopt.instagram.databinding.FragmentHomeBinding
import com.sopt.instagram.util.UiState
import com.sopt.instagram.util.binding.BindingFragment
import com.sopt.instagram.util.extension.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
    }

    private fun initAdapter() {
        viewModel.getPostListState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Success -> {
                    binding.rvHomePost.adapter = PostAdapter().apply {
                        submitList(viewModel.postList.value)
                    }
                }

                is UiState.Failure -> {
                    requireContext().showToast("fail")
                }

                is UiState.Error -> {
                    requireContext().showToast("error")
                }
            }
        }
    }
}
