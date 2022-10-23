package com.afrinaldi.beber.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.afrinaldi.beber.core.data.Resource
import com.afrinaldi.beber.core.ui.BreakingNewsAdapter
import com.afrinaldi.beber.core.utils.DATA
import com.afrinaldi.beber.databinding.FragmentHomeBinding
import com.afrinaldi.beber.presentation.detail.DetailActivity
import com.afrinaldi.beber.presentation.detail.DetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModel()
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val breakingNewsAdapter = BreakingNewsAdapter()
            breakingNewsAdapter.onItemClick = { news ->
                Intent(activity, DetailActivity::class.java).also {
                    it.putExtra(DATA, news)
                    startActivity(it)
                }
            }

            homeViewModel.news.observe(viewLifecycleOwner) { news ->
                if (news != null) {
                    when (news) {
                        is Resource.Success -> {
                            breakingNewsAdapter.setData(news.data)
                        }
                        is Resource.Error -> {
                            Toast.makeText(requireContext(), news.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                        is Resource.Loading -> {}

                    }
                }
            }

            binding.rvBreakingNews.setHasFixedSize(true)
            binding.rvBreakingNews.adapter = breakingNewsAdapter

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}