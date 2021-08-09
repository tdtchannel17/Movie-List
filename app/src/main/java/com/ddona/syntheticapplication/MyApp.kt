package com.ddona.syntheticapplication

import android.app.Application
import com.ddona.syntheticapplication.ui.viewmodel.MovieViewModel

class MyApp : Application() {
    companion object {
        lateinit var movieViewModel: MovieViewModel
    }

    override fun onCreate() {
        super.onCreate()
        movieViewModel = MovieViewModel()
    }
}