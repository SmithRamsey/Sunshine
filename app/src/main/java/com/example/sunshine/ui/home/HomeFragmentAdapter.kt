package com.example.sunshine.ui.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sunshine.R
import com.example.sunshine.databinding.HomeListItemBinding
import com.example.sunshine.model.ListItem
import com.example.sunshine.utils.bind
import com.example.sunshine.utils.setAuxiliaryTextVisibility

class HomeFragmentAdapter : RecyclerView.Adapter<HomeFragmentAdapter.HomeFragmentViewHolder>() {

    var weatherList: List<ListItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFragmentViewHolder =
        HomeFragmentViewHolder(parent.bind(R.layout.home_list_item))

    override fun onBindViewHolder(holder: HomeFragmentViewHolder, position: Int) {
        val weatherItem = weatherList[position]
        holder.binding.listItem = weatherItem
        holder.binding.rootView.setOnClickListener {
            holder.binding.itemHumidityText.setAuxiliaryTextVisibility()
            holder.binding.itemPressureText.setAuxiliaryTextVisibility()
            holder.binding.itemWindText.setAuxiliaryTextVisibility()
        }
    }

    override fun getItemCount(): Int = weatherList.size

    class HomeFragmentViewHolder(val binding: HomeListItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}