package com.ddona.syntheticapplication.ui.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.*
import com.ddona.syntheticapplication.api.ApiMoviePopular
import com.ddona.syntheticapplication.api.RetrofitUtils
import com.ddona.syntheticapplication.model.DetailMoviePopuLar
import com.ddona.syntheticapplication.model.MoviePopular
import com.ddona.syntheticapplication.model.MovieUpcoming
import com.ddona.syntheticapplication.model.SearchMovie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieViewModel : ViewModel() {
    private val apiMoviePopular: ApiMoviePopular
    val dataMoviePopular: MutableLiveData<MoviePopular>
    val dataDetailMoviePopular: MutableLiveData<DetailMoviePopuLar>
    val dataMovieUpcoming: MutableLiveData<MovieUpcoming>
    val dataSearchMovie: MutableLiveData<SearchMovie>

    init {
        apiMoviePopular = RetrofitUtils.createRetrofit()
        dataMoviePopular = MutableLiveData()
        dataDetailMoviePopular = MutableLiveData()
        dataMovieUpcoming = MutableLiveData()
        dataSearchMovie = MutableLiveData()
    }

    @SuppressLint("CheckResult")
    fun getDataMoviePopular(page: Int) {
        apiMoviePopular.getMoviePopulars("f7e87bc3904e28745a3e704a73861f54", "en-US", page)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    dataMoviePopular.value = it
                },
                {
                    Log.e("Error !!!", "----------------- " + it + " -----------------")
                }
            )
    }

    @SuppressLint("CheckResult")
    fun getDataDetailMoviePopular(idMovie: Int) {
        apiMoviePopular.getDetailMoviePopulars(idMovie, "f7e87bc3904e28745a3e704a73861f54", "en-US")
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    dataDetailMoviePopular.value = it
                },
                {
                    Log.e("Error !!!", "----------------- " + it + " -----------------")
                }
            )
    }

    @SuppressLint("CheckResult")
    fun getDataMovieUpcoming(page: Int) {
        apiMoviePopular.getMovieUpcoming("f7e87bc3904e28745a3e704a73861f54", "en-US", page)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    dataMovieUpcoming.value = it
                },
                {
                    Log.e("Error !!!", "----------------- " + it + " -----------------")
                }
            )
    }

    @SuppressLint("CheckResult")
    fun getSearchMovies(query: String): Disposable {
        val disposable =
            apiMoviePopular.getSearchMovies("f7e87bc3904e28745a3e704a73861f54", "en-US", query, 1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        dataSearchMovie.value = it
                    },
                    {
                        Log.e("Error !!!", "----------------- " + it + " -----------------")
                    }
                )
        return disposable
    }

}