package com.ddona.syntheticapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddona.syntheticapplication.R
import com.ddona.syntheticapplication.databinding.ItemSearchMovieBinding

class SearchMovieAdapter(val inter: SearchMovie) :
    RecyclerView.Adapter<SearchMovieAdapter.SearchMovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieHolder {
        return SearchMovieHolder(
            ItemSearchMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchMovieHolder, position: Int) {
        holder.binding.data = inter.getDataSearch(position)
    }

    override fun getItemCount() = inter.getCountSearch()

    interface SearchMovie {
        fun getCountSearch(): Int
        fun getDataSearch(position: Int): com.ddona.syntheticapplication.model.SearchMovie.Result
    }

    class SearchMovieHolder(val binding: ItemSearchMovieBinding) :
        RecyclerView.ViewHolder(binding.root)
}