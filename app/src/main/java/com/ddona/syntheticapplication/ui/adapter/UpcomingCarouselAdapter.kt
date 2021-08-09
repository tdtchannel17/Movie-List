package com.ddona.syntheticapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddona.syntheticapplication.databinding.ItemCarouselUpcomingBinding

class UpcomingCarouselAdapter(val inter: UpcomingCarousel) :
    RecyclerView.Adapter<UpcomingCarouselAdapter.UpcomingCarouseHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingCarouseHolder {
        return UpcomingCarouseHolder(
            ItemCarouselUpcomingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UpcomingCarouseHolder, position: Int) {
        holder.binding.data = inter.getDataCarouselUpUpComing(position)
        holder.itemView.setOnClickListener {
            inter.onClickItemCarouselUpComing(holder.adapterPosition)
        }
    }

    override fun getItemCount() = inter.getCountCarouselUpUpComing()

    interface UpcomingCarousel {
        fun getCountCarouselUpUpComing(): Int
        fun getDataCarouselUpUpComing(position: Int): com.ddona.syntheticapplication.model.MovieUpcoming.Result
        fun onClickItemCarouselUpComing(position: Int)
    }

    class UpcomingCarouseHolder(val binding: ItemCarouselUpcomingBinding) :
        RecyclerView.ViewHolder(binding.root)
}