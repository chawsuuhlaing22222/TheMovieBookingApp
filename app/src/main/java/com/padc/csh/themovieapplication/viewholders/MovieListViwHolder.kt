package com.padc.csh.themovieapplication.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.delegates.MovieListDelegate

class MovieListViwHolder(itemView: View,delegate: MovieListDelegate) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                delegate.onTapMovie()
            }

        }
}