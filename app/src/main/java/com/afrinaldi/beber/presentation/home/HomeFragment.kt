package com.afrinaldi.beber.presentation.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.updatePadding
import com.afrinaldi.beber.core.data.Resource
import com.afrinaldi.beber.core.ui.BreakingNewsAdapter
import com.afrinaldi.beber.core.ui.NewsPagerAdapter
import com.afrinaldi.beber.core.utils.DATA
import com.afrinaldi.beber.core.utils.Helper
import com.afrinaldi.beber.databinding.FragmentHomeBinding
import com.afrinaldi.beber.presentation.detail.DetailActivity
import com.afrinaldi.beber.presentation.detail.DetailViewModel
import com.amar.library.ui.interfaces.IScrollViewListener
import com.google.android.material.tabs.TabLayoutMediator
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
            setHeaderSticky()
            showBreakingNews()
            showCategoryNews()
        }
    }

    private fun setHeaderSticky() {
        binding.sticky.setScrollViewListener(object: IScrollViewListener{
            override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
                if(binding.sticky.isHeaderSticky) {
                    binding.tabs.updatePadding(top = 100)
                } else {
                    binding.tabs.updatePadding(top = 0)
                }
            }
        })
    }

    private fun showBreakingNews() {
        val breakingNewsAdapter = BreakingNewsAdapter()
        breakingNewsAdapter.onIntentClicked = { news ->
            Intent(activity, DetailActivity::class.java).also {
                it.putExtra(DATA, news)
                startActivity(it)
            }
        }

        breakingNewsAdapter.onBookmarkClicked = { news, item ->
            var statusBookmark = news.isBookmark
            statusBookmark = !statusBookmark
            detailViewModel.setBookmarkTourism(news, statusBookmark)
            item.ivBookmark.setImageDrawable(ContextCompat.getDrawable(requireContext(), Helper.setStatusBookmark(statusBookmark)))
        }

        homeViewModel.news.observe(viewLifecycleOwner) { news ->
            if (news != null) {
                when (news) {
                    is Resource.Success -> {
                        breakingNewsAdapter.clearData()
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

    private fun showCategoryNews() {
        val newsPagerAdapter = NewsPagerAdapter(detailViewModel)
        binding.viewPager.adapter = newsPagerAdapter

        TabLayoutMediator(binding.tabs, binding.viewPager) { category, position ->
            when(position) {
                0 -> category.text = "Olahraga"
                1 -> category.text = "Teknologi"
                2 -> category.text = "Bisnis"
                3 -> category.text = "Kesehatan"
            }
        }.attach()

        homeViewModel.sportNews.observe(viewLifecycleOwner){ sport ->
            if (sport != null) {
                when (sport) {
                    is Resource.Success -> {
                        newsPagerAdapter.setData(NewsPagerAdapter.Category.SPORT, sport.data)

                        homeViewModel.techNews.observe(viewLifecycleOwner){ tech ->
                            if (tech != null) {
                                when (tech) {
                                    is Resource.Success -> {
                                        newsPagerAdapter.setData(NewsPagerAdapter.Category.TECH, tech.data)

                                        homeViewModel.businessNews.observe(viewLifecycleOwner){ business ->
                                            if (business != null) {
                                                when (business) {
                                                    is Resource.Success -> {
                                                        newsPagerAdapter.setData(NewsPagerAdapter.Category.BUSINESS, business.data)

                                                        homeViewModel.healthNews.observe(viewLifecycleOwner){ health ->
                                                            if (health != null) {
                                                                when (health) {
                                                                    is Resource.Success -> {
                                                                        newsPagerAdapter.setData(NewsPagerAdapter.Category.HEALTH, health.data)
                                                                    }
                                                                    is Resource.Error -> {
                                                                        Toast.makeText(requireContext(), health.message, Toast.LENGTH_SHORT)
                                                                            .show()
                                                                    }
                                                                    is Resource.Loading -> {}
                                                                }
                                                            }
                                                        }
                                                    }
                                                    is Resource.Error -> {
                                                        Toast.makeText(requireContext(), business.message, Toast.LENGTH_SHORT)
                                                            .show()
                                                    }
                                                    is Resource.Loading -> {}
                                                }
                                            }
                                        }
                                    }
                                    is Resource.Error -> {
                                        Toast.makeText(requireContext(), tech.message, Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                    is Resource.Loading -> {}
                                }
                            }
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), sport.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                    is Resource.Loading -> {}
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}