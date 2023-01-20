package com.padc.csh.themovieapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.delegates.MovieCinemaDelegate
import com.padc.csh.themovieapplication.delegates.MovieCinemaSeatConditionDelegate
import com.padc.csh.themovieapplication.viewholders.MovieCinemaViewHolder
import kotlinx.android.synthetic.main.view_holder_cinema_item.view.*

class MovieCinemaAdapter(var cinemaDelegate: MovieCinemaDelegate,var cinemaSeatConditionDelegate: MovieCinemaSeatConditionDelegate): RecyclerView.Adapter<MovieCinemaViewHolder>() {

    private var selectedPosition=-1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCinemaViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_cinema_item,parent,false)
        return MovieCinemaViewHolder(view,cinemaDelegate)
    }

    override fun onBindViewHolder(holder: MovieCinemaViewHolder, position: Int) {

        holder.itemView.rvMovieAvailableTime.adapter=MovieCinemaSeatConditionAdapter(cinemaSeatConditionDelegate)
        holder.itemView.rvMovieAvailableTime.layoutManager=GridLayoutManager(holder.itemView.context,3)

        holder.itemView.tvSeeDetails.setOnClickListener {

           if(holder.itemView.rvMovieAvailableTime.visibility==View.VISIBLE){
               holder.itemView.rvMovieAvailableTime.visibility=View.GONE
           }else{
               holder.itemView.rvMovieAvailableTime.visibility=View.VISIBLE
           }
           if(holder.itemView.ivBookingInfo.visibility==View.VISIBLE){
               holder.itemView.ivBookingInfo.visibility=View.GONE
           }else{
               holder.itemView.ivBookingInfo.visibility=View.VISIBLE
           }

            //call delegate
            cinemaDelegate.onMovieCinema(position)
        }

        when(selectedPosition){
            position->{
                holder.itemView.rvMovieAvailableTime.visibility=View.VISIBLE
                holder.itemView.ivBookingInfo.visibility=View.VISIBLE
            }
            else->{
                holder.itemView.rvMovieAvailableTime.visibility=View.GONE
                holder.itemView.ivBookingInfo.visibility=View.GONE
            }

        }
    }

    public fun setSelectedPosition(position: Int){
        selectedPosition=position
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
       return 5
    }
}