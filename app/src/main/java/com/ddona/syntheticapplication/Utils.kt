package com.ddona.syntheticapplication

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object Utils {
    // set text
    @JvmStatic
    @BindingAdapter("setText")
    fun setText(tv: TextView, content: String?) {
        tv.setText(content)
    }

    @JvmStatic
    @BindingAdapter("setText")
    fun setText(tv: TextView, content: Int) {
        tv.setText(content.toString())
    }

    @JvmStatic
    @BindingAdapter("setText")
    fun setText(tv: TextView, content: Double) {
        val text = "Vote average : " + content.toString() + "/10"
        tv.setText(text)
    }

    // set image
    @JvmStatic
    @BindingAdapter("setImageLink")
    fun setImageLink(iv: ImageView, linkImage: String) {
        val link = "https://image.tmdb.org/t/p/w500/" + linkImage
        Glide.with(iv.context)
            .load(link)
            .into(iv)
    }
}