package com.padc.csh.themovieapplication.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapplication.delegates.MovieListDelegate
import com.padc.csh.themovieapplication.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_movie_item.view.*

class NowShowingMovieListViewHolder(itemView: View,delegate: MovieListDelegate) : RecyclerView.ViewHolder(itemView) {

    fun bindData(movieVO: MovieVO){

        Glide.with(itemView.context).load("$IMAGE_BASE_URL${movieVO.posterPath}").into(itemView.ivMovie)
        itemView.tvMovieName.text=movieVO.originalTtitle


    }
}