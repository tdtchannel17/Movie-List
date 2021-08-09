package com.ddona.syntheticapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddona.syntheticapplication.databinding.ItemMoviePopularBinding
import com.ddona.syntheticapplication.databinding.ItemMovieUpcomingBinding
import com.ddona.syntheticapplication.model.MovieUpcoming

class MovieUpcomingAdapter(val inter: MovieUpcoming) :
    RecyclerView.Adapter<MovieUpcomingAdapter.MovieUpcomingHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieUpcomingHolder {
        return MovieUpcomingHolder(
            ItemMovieUpcomingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieUpcomingHolder, position: Int) {
        holder.binding.data = inter.getDataUpcoming(position)
        holder.itemView.setOnClickListener {
            inter.onClickItemUpcoming(holder.adapterPosition)
        }
    }

    override fun getItemCount() = inter.getCountUpcoming()

    interface MovieUpcoming {
        fun getCountUpcoming(): Int
        fun getDataUpcoming(position: Int): com.ddona.syntheticapplication.model.MovieUpcoming.Result
        fun onClickItemUpcoming(position: Int)
    }

    class MovieUpcomingHolder(val binding: ItemMovieUpcomingBinding) :
        RecyclerView.ViewHolder(binding.root)
}