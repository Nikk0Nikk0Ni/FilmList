package com.niko.filmlist.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.niko.filmlist.R
import com.niko.filmlist.domain.models.Review
import com.niko.filmlist.presentation.holders.ReviewHolder
import com.niko.filmlist.presentation.utils.ReviewUtils

class ReviewAdapter : ListAdapter<Review, ReviewHolder>(ReviewUtils()) {
    var uploadReview: (() -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.review_layout, parent, false)
        return ReviewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewHolder, position: Int) {
        holder.bind(getItem(position))
        if (position == currentList.size - 5)
            uploadReview?.let {
                it()
            }
    }
}