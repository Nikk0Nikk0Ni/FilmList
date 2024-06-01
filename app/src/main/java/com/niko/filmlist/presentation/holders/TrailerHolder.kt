package com.niko.filmlist.presentation.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.niko.filmlist.databinding.TrailerItemLayoutBinding
import com.niko.filmlist.domain.models.Trailer

class TrailerHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = TrailerItemLayoutBinding.bind(view)
    fun bind(trailer : Trailer,itemClick : ((Trailer)->Unit)?){
        binding.trailerName.text = trailer.name
        itemView.setOnClickListener{
            itemClick?.let {
                it(trailer)
            }
        }
    }
}