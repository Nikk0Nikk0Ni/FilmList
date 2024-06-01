package com.niko.filmlist.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.niko.filmlist.R
import com.niko.filmlist.domain.models.Trailer
import com.niko.filmlist.presentation.holders.TrailerHolder
import com.niko.filmlist.presentation.utils.TrailerUtils

class TrailerAdapter : ListAdapter<Trailer, TrailerHolder>(TrailerUtils()) {
    var itemClick : ((Trailer)->Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.trailer_item_layout, parent, false)
        return TrailerHolder(view)
    }

    override fun onBindViewHolder(holder: TrailerHolder, position: Int) {
        holder.bind(getItem(position),itemClick)
    }
}