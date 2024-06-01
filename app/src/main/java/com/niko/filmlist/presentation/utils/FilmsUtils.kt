package com.niko.filmlist.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.niko.filmlist.domain.models.Film

class FilmsUtils : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }
}