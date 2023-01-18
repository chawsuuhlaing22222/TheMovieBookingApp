package com.padc.csh.themovieapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.delegates.MovieListDelegate
import com.padc.csh.themovieapplication.viewholders.MovieListViwHolder

class MovieListAdapter(val delegate:MovieListDelegate): RecyclerView.Adapter<MovieListViwHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViwHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_item,parent,false)
        return MovieListViwHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: MovieListViwHolder, position: Int) {

    }

    override fun getItemCount(): Int {
       return 12
    }
}