package com.padc.csh.themovieapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.viewholders.MovieGenreViewHolder
//not use
class MovieGenreAdapter: RecyclerView.Adapter<MovieGenreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieGenreViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_genre,parent,false)
        return MovieGenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieGenreViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 4
    }
}