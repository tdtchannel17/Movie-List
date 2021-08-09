package com.ddona.syntheticapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddona.syntheticapplication.databinding.ItemMoviePopularBinding

class MoviePopularAdapter(val inter: MoviePopular) :
    RecyclerView.Adapter<MoviePopularAdapter.MoviePopularHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePopularHolder {
        return MoviePopularHolder(
            ItemMoviePopularBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviePopularHolder, position: Int) {
        holder.binding.data = inter.getDataPopular(position)
        holder.itemView.setOnClickListener {
            inter.onClickItemPopular(holder.adapterPosition)
        }
    }

    override fun getItemCount() = inter.getCountPopular()

    interface MoviePopular {
        fun getCountPopular(): Int
        fun getDataPopular(position: Int): com.ddona.syntheticapplication.model.MoviePopular.Result
        fun onClickItemPopular(position: Int)
    }

    class MoviePopularHolder(val binding: ItemMoviePopularBinding) :
        RecyclerView.ViewHolder(binding.root)
}