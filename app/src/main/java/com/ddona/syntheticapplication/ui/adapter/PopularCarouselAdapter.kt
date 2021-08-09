package com.ddona.syntheticapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddona.syntheticapplication.databinding.ItemCarouselPopularBinding

class PopularCarouselAdapter(val inter: PopularCarousel) :
    RecyclerView.Adapter<PopularCarouselAdapter.PopularCarouselHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularCarouselHolder {
        return PopularCarouselHolder(
            ItemCarouselPopularBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PopularCarouselHolder, position: Int) {
        holder.binding.data = inter.getDataCarouselPopular(position)
        holder.itemView.setOnClickListener {
            inter.onClickItemCarouselPopular(holder.adapterPosition)
        }
    }

    override fun getItemCount() = inter.getCountCarouselPopular()

    interface PopularCarousel {
        fun getCountCarouselPopular(): Int
        fun getDataCarouselPopular(position: Int): com.ddona.syntheticapplication.model.MoviePopular.Result
        fun onClickItemCarouselPopular(position: Int)
    }

    class PopularCarouselHolder(val binding: ItemCarouselPopularBinding) :
        RecyclerView.ViewHolder(binding.root)
}