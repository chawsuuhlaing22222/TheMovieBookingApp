package com.padc.csh.themovieapplication.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.delegates.MovieListDelegate
import kotlinx.android.synthetic.main.view_holder_movie_item.view.*

class MovieListViwHolder(itemView: View,delegate: MovieListDelegate,flag:String) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {

                //delegate.onTapMovie()
            }

        }
}