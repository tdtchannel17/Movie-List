package com.ddona.syntheticapplication.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.ddona.syntheticapplication.MyApp
import com.ddona.syntheticapplication.R
import com.ddona.syntheticapplication.databinding.FragmentMovieBinding
import com.ddona.syntheticapplication.model.MoviePopular
import com.ddona.syntheticapplication.model.MovieUpcoming
import com.ddona.syntheticapplication.model.SearchMovie
import com.ddona.syntheticapplication.ui.activity.MainActivity
import com.ddona.syntheticapplication.ui.adapter.*

class MovieFragment : Fragment(), MoviePopularAdapter.MoviePopular,
    MovieUpcomingAdapter.MovieUpcoming, PopularCarouselAdapter.PopularCarousel,
    UpcomingCarouselAdapter.UpcomingCarousel, SearchView.OnQueryTextListener,
    SearchMovieAdapter.SearchMovie, SearchCarouselAdapter.UpcomingCarousel {
    private lateinit var binding: FragmentMovieBinding
    private var listMoviePopular = mutableListOf<MoviePopular.Result>()
    private var listMovieUpcoming = mutableListOf<MovieUpcoming.Result>()
    private var listMovieSearch = mutableListOf<SearchMovie.Result>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        inits()
        getDataMovie()
        getListMovieUpcoming()
        return binding.root
    }

    private fun inits() {
        listMoviePopular.clear()
        listMovieUpcoming.clear()
        listMovieSearch.clear()
        binding.svMovie.setOnQueryTextListener(this)
        val category = arrayOf("Movie upcoming", "Movie popular")
        val adapterCategory = ArrayAdapter(context!!, R.layout.selected_item_page, category)
        adapterCategory.setDropDownViewResource(R.layout.dropdown_item)
        binding.spinnerCategoryMovie.adapter = adapterCategory
        binding.spinnerCategoryMovie.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (category[position]) {
                        "Movie popular" -> {
                            binding.progressBar.visibility = View.VISIBLE
                            getListMoviePopular()
                            val page = arrayOf(1, 2, 3, 4, 5)
                            val adapterPage =
                                ArrayAdapter(context!!, R.layout.selected_item_page, page)
                            adapterPage.setDropDownViewResource(R.layout.dropdown_item)
                            binding.spinnerPage.adapter = adapterPage
                            binding.spinnerPage.onItemSelectedListener =
                                object : AdapterView.OnItemSelectedListener {
                                    override fun onItemSelected(
                                        parent: AdapterView<*>?,
                                        view: View?,
                                        position: Int,
                                        id: Long
                                    ) {
                                        MyApp.movieViewModel.getDataMoviePopular(page[position])
                                        binding.progressBar.visibility = View.GONE
                                    }

                                    override fun onNothingSelected(parent: AdapterView<*>?) {}

                                }
                        }
                        "Movie upcoming" -> {
                            binding.progressBar.visibility = View.VISIBLE
                            getListMovieUpcoming()
                            val page = arrayOf(1, 2, 3, 4, 5)
                            val adapterPage =
                                ArrayAdapter(context!!, R.layout.selected_item_page, page)
                            adapterPage.setDropDownViewResource(R.layout.dropdown_item)
                            binding.spinnerPage.adapter = adapterPage
                            binding.spinnerPage.onItemSelectedListener =
                                object : AdapterView.OnItemSelectedListener {
                                    override fun onItemSelected(
                                        parent: AdapterView<*>?,
                                        view: View?,
                                        position: Int,
                                        id: Long
                                    ) {
                                        MyApp.movieViewModel.getDataMovieUpcoming(page[position])
                                        binding.progressBar.visibility = View.GONE
                                    }

                                    override fun onNothingSelected(parent: AdapterView<*>?) {}

                                }
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}

            }
    }

    private fun getDataMovie() {
        MyApp.movieViewModel.getDataMovieUpcoming(1)
    }

    private fun getListMoviePopular() {
        binding.progressBar.visibility = View.VISIBLE
        MyApp.movieViewModel.dataMoviePopular.observe(this, Observer {
            if (listMovieUpcoming.isNotEmpty()) {
                listMovieUpcoming.clear()
            }
            if (listMoviePopular.isNotEmpty()) {
                listMoviePopular.clear()
            }
            for (i in 0 until it.results.size) {
                if (it.results[i].posterPath == null || it.results[i].backdropPath == null) {
                    Log.d("null", "null : " + i)
                } else {
                    listMoviePopular.add(it.results[i])
                }
            }

            binding.crTop.adapter = PopularCarouselAdapter(this)
            binding.crTop.layoutManager = binding.crTop.getCarouselLayoutManager()
            binding.crTop.set3DItem(true)
            binding.crTop.setAlpha(true)
            binding.crTop.adapter?.notifyDataSetChanged()

            binding.rcPopular.layoutManager = GridLayoutManager(context, 2)
            binding.rcPopular.adapter = MoviePopularAdapter(this)
            binding.rcPopular.adapter?.notifyDataSetChanged()
            binding.progressBar.visibility = View.GONE
        })
    }

    private fun getListMovieUpcoming() {
        binding.progressBar.visibility = View.VISIBLE
        MyApp.movieViewModel.dataMovieUpcoming.observe(this, Observer {
            if (listMovieUpcoming.isNotEmpty()) {
                listMovieUpcoming.clear()
            }
            if (listMoviePopular.isNotEmpty()) {
                listMoviePopular.clear()
            }
            for (i in 0 until it.results.size) {
                if (it.results[i].posterPath == null || it.results[i].backdropPath == null) {
                    Log.d("null", "null : " + i)
                } else {
                    listMovieUpcoming.add(it.results[i])
                }
            }

            binding.crTop.adapter = UpcomingCarouselAdapter(this)
            binding.crTop.layoutManager = binding.crTop.getCarouselLayoutManager()
            binding.crTop.set3DItem(true)
            binding.crTop.setAlpha(true)
            binding.crTop.adapter?.notifyDataSetChanged()

            binding.rcPopular.layoutManager = GridLayoutManager(context, 2)
            binding.rcPopular.adapter = MovieUpcomingAdapter(this)
            binding.rcPopular.adapter?.notifyDataSetChanged()
            binding.progressBar.visibility = View.GONE
        })
    }

    // popular
    override fun getCountPopular(): Int {
        return listMoviePopular.size
    }

    override fun getDataPopular(position: Int): MoviePopular.Result {
        return listMoviePopular[position]
    }

    override fun onClickItemPopular(position: Int) {
        val idMovie = listMoviePopular[position].id
        (activity as MainActivity).addDetailMoviePopular(idMovie)
    }

    override fun getCountCarouselPopular(): Int {
        return listMoviePopular.size
    }

    override fun getDataCarouselPopular(position: Int): MoviePopular.Result {
        return listMoviePopular[position]
    }

    override fun onClickItemCarouselPopular(position: Int) {
        val idMovie = listMoviePopular[position].id
        (activity as MainActivity).addDetailMoviePopular(idMovie)
    }

    // upcoming
    override fun getCountUpcoming(): Int {
        return listMovieUpcoming.size
    }

    override fun getDataUpcoming(position: Int): MovieUpcoming.Result {
        return listMovieUpcoming[position]
    }

    override fun onClickItemUpcoming(position: Int) {
        val idMovie = listMovieUpcoming[position].id
        (activity as MainActivity).addDetailMoviePopular(idMovie)
    }

    override fun getCountCarouselUpUpComing(): Int {
        return listMovieUpcoming.size
    }

    override fun getDataCarouselUpUpComing(position: Int): MovieUpcoming.Result {
        return listMovieUpcoming[position]
    }

    override fun onClickItemCarouselUpComing(position: Int) {
        val idMovie = listMovieUpcoming[position].id
        (activity as MainActivity).addDetailMoviePopular(idMovie)
    }

    // search text
    override fun onQueryTextSubmit(keyWord: String): Boolean {
        listMovieSearch.clear()
        MyApp.movieViewModel.getSearchMovies(keyWord)
        MyApp.movieViewModel.dataSearchMovie.observe(this, Observer {
            for (i in 0 until it.results.size) {
                if (it.results[i].posterPath != null || it.results[i].backdropPath == null) {
                    listMovieSearch.add(it.results[i])
                }
            }

            binding.crTop.adapter = SearchCarouselAdapter(this)
            binding.crTop.layoutManager = binding.crTop.getCarouselLayoutManager()
            binding.crTop.set3DItem(true)
            binding.crTop.setAlpha(true)
            binding.crTop.adapter?.notifyDataSetChanged()

            binding.rcPopular.layoutManager = GridLayoutManager(context, 2)
            binding.rcPopular.adapter = SearchMovieAdapter(this)
            binding.rcPopular.adapter?.notifyDataSetChanged()
        })
        return true
    }

    override fun onQueryTextChange(keyWord: String): Boolean {
        MyApp.movieViewModel.getSearchMovies(keyWord)
        MyApp.movieViewModel.dataSearchMovie.observe(this, Observer {
            for (i in 0 until it.results.size) {
                if (it.results[i].posterPath == null || it.results[i].backdropPath == null) {
                    Log.d("null", "null : " + i)
                } else {
                    listMovieSearch.add(it.results[i])
                }
            }

            binding.crTop.adapter = SearchCarouselAdapter(this)
            binding.crTop.layoutManager = binding.crTop.getCarouselLayoutManager()
            binding.crTop.set3DItem(true)
            binding.crTop.setAlpha(true)
            binding.crTop.adapter?.notifyDataSetChanged()

            binding.rcPopular.layoutManager = GridLayoutManager(context, 2)
            binding.rcPopular.adapter = SearchMovieAdapter(this)
            binding.rcPopular.adapter?.notifyDataSetChanged()
        })
        return true
    }

    override fun getCountSearch(): Int {
        return listMovieSearch.size
    }

    override fun getDataSearch(position: Int): SearchMovie.Result {
        return listMovieSearch[position]
    }

    override fun getCountCarouselSearch(): Int {
        return listMovieSearch.size
    }

    override fun getDataCarouselSearch(position: Int): SearchMovie.Result {
        return listMovieSearch[position]
    }

}