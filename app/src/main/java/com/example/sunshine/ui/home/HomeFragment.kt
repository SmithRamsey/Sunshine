package com.example.sunshine.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sunshine.R
import com.example.sunshine.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val homeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }
    private val homeFragmentAdapter by lazy {
        HomeFragmentAdapter()
    }
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.getWeather()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.rvHome.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeFragmentAdapter
            val itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            getDrawable(context, R.drawable.divider)?.let { itemDecoration.setDrawable(it) }
            addItemDecoration(itemDecoration)
        }
        homeViewModel.weatherPayload.observe(viewLifecycleOwner, { payLoad ->
            binding.listItem = payLoad.list?.firstOrNull()
            binding.weatherItem = payLoad.list?.firstOrNull()?.weather?.firstOrNull()
            Log.d("testing", "onCreateView: ${payLoad.list?.firstOrNull()?.feels_like?.day}")
            binding.locationText.text = payLoad.city?.name
            payLoad.list?.filterNotNull()?.let { weatherList ->
                homeFragmentAdapter.weatherList = weatherList
            }
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        homeViewModel.onDestroy()
    }
}