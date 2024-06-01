package com.niko.filmlist.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.niko.filmlist.domain.models.Trailer

class TrailerUtils : DiffUtil.ItemCallback<Trailer>() {
    override fun areItemsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
        return oldItem == newItem
    }
}