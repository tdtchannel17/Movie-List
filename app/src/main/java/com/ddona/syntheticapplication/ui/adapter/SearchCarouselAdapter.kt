package com.ddona.syntheticapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddona.syntheticapplication.databinding.ItemCarouselSearchBinding

class SearchCarouselAdapter(val inter: UpcomingCarousel) :
    RecyclerView.Adapter<SearchCarouselAdapter.SearchCarouseHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchCarouseHolder {
        return SearchCarouseHolder(
            ItemCarouselSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchCarouseHolder, position: Int) {
        holder.binding.data = inter.getDataCarouselSearch(position)
    }

    override fun getItemCount() = inter.getCountCarouselSearch()

    interface UpcomingCarousel {
        fun getCountCarouselSearch(): Int
        fun getDataCarouselSearch(position: Int): com.ddona.syntheticapplication.model.SearchMovie.Result
    }

    class SearchCarouseHolder(val binding: ItemCarouselSearchBinding) :
        RecyclerView.ViewHolder(binding.root)
}