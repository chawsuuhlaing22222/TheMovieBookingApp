package com.padc.csh.themovieapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.delegates.MovieListDelegate
import com.padc.csh.themovieapplication.viewholders.NowShowingMovieListViewHolder

class NowShowingMovieAdapter(var delegate: MovieListDelegate): RecyclerView.Adapter<NowShowingMovieListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NowShowingMovieListViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_item,parent,false)
        return NowShowingMovieListViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: NowShowingMovieListViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            delegate.onTapNowShowingMovie()
        }
    }

    override fun getItemCount(): Int {
       return 10
    }
}