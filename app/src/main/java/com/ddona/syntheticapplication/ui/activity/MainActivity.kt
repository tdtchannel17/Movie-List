package com.ddona.syntheticapplication.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ddona.syntheticapplication.R
import com.ddona.syntheticapplication.databinding.ActivityMainBinding
import com.ddona.syntheticapplication.ui.fragment.DetailMoviePopularFragment
import com.ddona.syntheticapplication.ui.fragment.MovieFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        addMoviePopularFragment()
    }

    private fun addMoviePopularFragment() {
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = MovieFragment()

        tran
            .add(R.id.content, fr, "movie")
            .addToBackStack("movie")
            .commit()
    }

    fun addDetailMoviePopular(idMovie: Int) {
        val manager = supportFragmentManager
        val tran = manager.beginTransaction()
        val fr = DetailMoviePopularFragment()
        val b = Bundle()
        b.putInt("id_movie", idMovie)
        fr.arguments = b

        tran
            .replace(R.id.content, fr, "detail")
            .addToBackStack("detail")
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentByTag("movie")!!.isVisible) {
            onStop()
        }
        super.onBackPressed()
    }
}