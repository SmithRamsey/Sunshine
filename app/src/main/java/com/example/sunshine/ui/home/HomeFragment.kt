package com.example.sunshine.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sunshine.R
import com.example.sunshine.databinding.FragmentHomeBinding
import com.example.sunshine.model.WeatherPayload

class HomeFragment : Fragment() {

    private val homeViewModel by lazy {
        ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
    }
    private val homeFragmentAdapter by lazy {
        HomeFragmentAdapter()
    }
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.rvHome.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeFragmentAdapter
            getDrawable(context, R.drawable.divider)?.let { itemDecoration.setDrawable(it) }
            addItemDecoration(itemDecoration)
        }
        binding.swipeRefreshLayout.setOnRefreshListener { homeViewModel.getWeather() }
        homeViewModel.weatherPayload.observe(viewLifecycleOwner, weatherPayloadObserver)
        homeViewModel.isLoading.observe(viewLifecycleOwner, isLoadingObserver)
        return root
    }

    private val isLoadingObserver = Observer<Boolean> {
        binding.swipeRefreshLayout.isRefreshing = it
    }

    private val weatherPayloadObserver = Observer<WeatherPayload> { payLoad ->
        binding.listItem = payLoad.list?.firstOrNull()
        binding.weatherItem = payLoad.list?.firstOrNull()?.weather?.firstOrNull()
        binding.locationText.text = payLoad.city?.name
        payLoad.list?.filterNotNull()?.let { weatherList ->
            homeFragmentAdapter.weatherList = weatherList
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}