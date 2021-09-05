package com.example.sunshine.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sunshine.R
import com.example.sunshine.databinding.HomeListItemBinding
import com.example.sunshine.model.ListItem
import com.example.sunshine.utils.bind
import com.example.sunshine.utils.setAuxiliaryViewVisibility

class HomeFragmentAdapter : RecyclerView.Adapter<HomeFragmentAdapter.HomeFragmentViewHolder>() {

    var weatherList: List<ListItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFragmentViewHolder =
        HomeFragmentViewHolder(parent.bind(R.layout.home_list_item))

    override fun onBindViewHolder(holder: HomeFragmentViewHolder, position: Int) {
        val weatherItem = weatherList[position]
        holder.binding.listItem = weatherItem
        holder.binding.weatherItem = weatherItem.weather?.firstOrNull()
        holder.binding.rootView.setOnClickListener {
            holder.binding.itemHumidityText.setAuxiliaryViewVisibility()
            holder.binding.itemPressureText.setAuxiliaryViewVisibility()
            holder.binding.itemWindText.setAuxiliaryViewVisibility()
        }
    }

    override fun getItemCount(): Int = weatherList.size

    class HomeFragmentViewHolder(val binding: HomeListItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}