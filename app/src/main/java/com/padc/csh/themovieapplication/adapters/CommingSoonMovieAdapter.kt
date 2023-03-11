package com.padc.csh.themovieapplication.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.delegates.MovieListDelegate
import com.padc.csh.themovieapplication.viewholders.CommingSoonMovieViewHolder
import kotlinx.android.synthetic.main.view_holder_movie_item.view.*

class CommingSoonMovieAdapter(var delegate: MovieListDelegate) :
    RecyclerView.Adapter<CommingSoonMovieViewHolder>() {

    private var movieList: List<MovieVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommingSoonMovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_movie_item, parent, false)
        return CommingSoonMovieViewHolder(view, delegate)
    }

    override fun onBindViewHolder(holder: CommingSoonMovieViewHolder, position: Int) {
        holder.itemView.tvCommingShowDate.visibility = View.VISIBLE
        var movieVO:MovieVO?=null
        if(movieList.isNotEmpty()){
            movieVO=movieList.get(position)
            holder.bindData(movieVO)
        }

        holder.itemView.setOnClickListener {
            movieVO?.let {
                delegate.onTapCommingSoonMovie(it.id.toString())
            }

        }


    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(it: List<MovieVO>) {
        movieList = it
        notifyDataSetChanged()
    }
}