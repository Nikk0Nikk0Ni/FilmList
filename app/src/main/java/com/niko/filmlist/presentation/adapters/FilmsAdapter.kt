package com.niko.filmlist.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.niko.filmlist.R
import com.niko.filmlist.domain.models.Film
import com.niko.filmlist.presentation.holders.FilmsHolder
import com.niko.filmlist.presentation.utils.FilmsUtils

class FilmsAdapter: ListAdapter<Film,FilmsHolder>(FilmsUtils()) {
    var  onReachEndListener : (()->Unit)? = null
    var click : ((Film)->Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.film_item_layout,parent,false)
        return FilmsHolder(view)
    }

    override fun onBindViewHolder(holder: FilmsHolder, position: Int) {
        holder.bind(getItem(position),click)
        if (position == currentList.size-10){
            onReachEndListener?.invoke()
        }
    }
}