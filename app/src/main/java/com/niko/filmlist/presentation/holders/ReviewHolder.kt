package com.niko.filmlist.presentation.holders

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.niko.filmlist.R
import com.niko.filmlist.databinding.ReviewLayoutBinding
import com.niko.filmlist.domain.models.Review
private const val NEGATIVE = "Негативный"
private const val NEUTRAL = "Нейтральный"
private const val POSITIVE = "Позитивный"
class ReviewHolder(private val view:View): RecyclerView.ViewHolder(view) {
    private val binding = ReviewLayoutBinding.bind(view)
    fun bind(review : Review){
        when(review.type){
            NEGATIVE->{binding.cardViewReview.setBackgroundColor(ContextCompat.getColor(view.context,
                R.color.red))}
            POSITIVE->{binding.cardViewReview.setBackgroundColor(ContextCompat.getColor(view.context,
                R.color.green))}
            NEUTRAL->{binding.cardViewReview.setBackgroundColor(ContextCompat.getColor(view.context,
                R.color.orange))}
            else->{binding.cardViewReview.setBackgroundColor(ContextCompat.getColor(view.context,
                R.color.white))}
        }
        binding.tvName.text = review.author
        binding.tvReviewTitle.text = review.title
        binding.tvReview.text = review.review
    }
}