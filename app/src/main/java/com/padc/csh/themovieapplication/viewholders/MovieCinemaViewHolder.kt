package com.padc.csh.themovieapplication.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.data.vos.CinemaVO
import com.padc.csh.themovieapplication.delegates.MovieCinemaDelegate
import kotlinx.android.synthetic.main.view_holder_cinema_item.view.*

class MovieCinemaViewHolder(itemView: View,delegate: MovieCinemaDelegate) :RecyclerView.ViewHolder(itemView) {
    fun bindData(cinema: CinemaVO) {
        itemView.tvCinemaName.text=cinema.name
    }
}