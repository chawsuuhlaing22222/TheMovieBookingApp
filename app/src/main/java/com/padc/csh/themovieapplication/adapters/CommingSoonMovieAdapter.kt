package com.padc.csh.themovieapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.delegates.MovieListDelegate
import com.padc.csh.themovieapplication.viewholders.CommingSoonMovieViewHolder
import kotlinx.android.synthetic.main.view_holder_movie_item.view.*

class CommingSoonMovieAdapter(var delegate: MovieListDelegate): RecyclerView.Adapter<CommingSoonMovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommingSoonMovieViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_item,parent,false)
        return CommingSoonMovieViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: CommingSoonMovieViewHolder, position: Int) {
         holder.itemView.tvCommingShowDate.visibility=View.VISIBLE

        holder.itemView.setOnClickListener {
            delegate.onTapCommingSoonMovie()
        }
    }

    override fun getItemCount(): Int {
        return 12
    }
}