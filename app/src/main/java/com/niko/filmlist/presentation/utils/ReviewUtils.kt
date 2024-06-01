package com.niko.filmlist.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.niko.filmlist.domain.models.Review

class ReviewUtils: DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }
}