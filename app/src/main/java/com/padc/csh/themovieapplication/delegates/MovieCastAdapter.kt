package com.padc.csh.themovieapplication.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.viewholders.MovieCastViewHolder

class MovieCastAdapter: RecyclerView.Adapter<MovieCastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCastViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_cast,parent,false)
        return MovieCastViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieCastViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
       return 10
    }
}