package com.niko.filmlist.presentation.holders

import android.content.Context
import android.content.res.ColorStateList
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.niko.filmlist.R
import com.niko.filmlist.databinding.FilmItemLayoutBinding
import com.niko.filmlist.domain.models.Film
import com.squareup.picasso.Picasso

class FilmsHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val binding = FilmItemLayoutBinding.bind(view)
    fun bind(film: Film,click : ((Film)->Unit)?) {
        if(film.poster?.url != null)
            Picasso.get().load(film.poster.url).resize(1024,1024).centerInside().into(binding.imgPoster)
        else
            binding.imgPoster.setImageResource(R.drawable.user_svgrepo_com)
        film.rating?.let {
            it.kp?.let { rating ->
                when {
                    rating < 5f -> binding.tvRating.backgroundTintList = ColorStateList.valueOf(
                        view.context.getColor(
                            R.color.red
                        )
                    )
                    rating > 5 && rating < 7 -> binding.tvRating.backgroundTintList = ColorStateList.valueOf(
                        view.context.getColor(
                            R.color.orange
                        )
                    )
                    else -> binding.tvRating.backgroundTintList = ColorStateList.valueOf(
                        view.context.getColor(
                            R.color.green
                        )
                    )
                }
            }
        }
        binding.tvRating.text = film.rating?.kp.toString()
        itemView.setOnClickListener{
            click?.let {
                it(film)
            }
        }
    }
}