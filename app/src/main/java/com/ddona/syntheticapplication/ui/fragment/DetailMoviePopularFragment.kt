package com.ddona.syntheticapplication.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.ddona.syntheticapplication.MyApp
import com.ddona.syntheticapplication.databinding.FragmentDetailMoviePopularBinding

class DetailMoviePopularFragment : Fragment() {
    private lateinit var binding: FragmentDetailMoviePopularBinding
    private var idMovie: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailMoviePopularBinding.inflate(inflater, container, false)
        idMovie = arguments?.getInt("id_movie")
        MyApp.movieViewModel.getDataDetailMoviePopular(idMovie!!)
        MyApp.movieViewModel.dataDetailMoviePopular.observe(this, Observer {
            val link = "https://image.tmdb.org/t/p/w500/" + it.posterPath
            Glide.with(binding.ivBackDropPath.context)
                .load(link)
                .into(binding.ivBackDropPath)
            val name = "Name : " + it.originalTitle
            val VoteAverage = "Vote average : " + it.voteAverage.toString() + "/10"
            val ReleaseDate = "Release date : " + it.releaseDate
            val overview = "Overview : " + it.overview
            binding.tvNameMovie.setText(name)
            binding.tvVoteAverage.setText(VoteAverage)
            binding.tvReleaseDate.setText(ReleaseDate)
            binding.tvOverview.setText(overview)
        })
        return binding.root
    }
}