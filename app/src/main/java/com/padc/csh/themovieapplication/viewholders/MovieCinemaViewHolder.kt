package com.padc.csh.themovieapplication.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.data.vos.CinemaVO
import com.padc.csh.themovieapplication.delegates.MovieCinemaDelegate
import kotlinx.android.synthetic.main.view_holder_cinema_item.view.*

class MovieCinemaViewHolder(itemView: View, delegate: MovieCinemaDelegate) :
    RecyclerView.ViewHolder(itemView) {

    fun bindData(cinema: CinemaVO) {

        itemView.tvCinemaName.text = cinema.name
        if (cinema.isSelected == true) {
            itemView.rvMovieAvailableTime.visibility = View.VISIBLE
            itemView.ivBookingInfo.visibility = View.VISIBLE
            itemView.tvBookingSeatingInfo.visibility = View.VISIBLE
        } else {
            itemView.rvMovieAvailableTime.visibility = View.GONE
            itemView.ivBookingInfo.visibility = View.GONE
            itemView.tvBookingSeatingInfo.visibility = View.GONE
        }

    }

}