package com.ddona.syntheticapplication.api

import com.ddona.syntheticapplication.model.DetailMoviePopuLar
import com.ddona.syntheticapplication.model.MoviePopular
import com.ddona.syntheticapplication.model.MovieUpcoming
import com.ddona.syntheticapplication.model.SearchMovie
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiMoviePopular {

    @GET("movie/popular")
    fun getMoviePopulars(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Observable<MoviePopular>

    @GET("movie/{movie_id}")
    fun getDetailMoviePopulars(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Observable<DetailMoviePopuLar>

    @GET("movie/upcoming")
    fun getMovieUpcoming(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Observable<MovieUpcoming>

    @GET("search/collection")
    fun getSearchMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") movieId: String,
        @Query("page") page: Int
    ): Observable<SearchMovie>
}