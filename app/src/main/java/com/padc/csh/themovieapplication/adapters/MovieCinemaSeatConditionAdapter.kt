package com.padc.csh.themovieapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.delegates.MovieCinemaDelegate
import com.padc.csh.themovieapplication.delegates.MovieCinemaSeatConditionDelegate
import com.padc.csh.themovieapplication.viewholders.MovieCinemaSeatConditionViewHolder
import com.padc.csh.themovieapplication.viewholders.MovieCinemaViewHolder

class MovieCinemaSeatConditionAdapter(var movieCinemaSeatConditionDelegate: MovieCinemaSeatConditionDelegate): RecyclerView.Adapter<MovieCinemaSeatConditionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCinemaSeatConditionViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_cinema_seat_condition,parent,false)
        return MovieCinemaSeatConditionViewHolder(view,movieCinemaSeatConditionDelegate)
    }

    override fun getItemCount(): Int {
        return 7
    }

    override fun onBindViewHolder(holder: MovieCinemaSeatConditionViewHolder, position: Int) {

    }
}