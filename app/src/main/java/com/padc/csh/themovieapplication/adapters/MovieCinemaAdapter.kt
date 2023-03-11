package com.padc.csh.themovieapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.data.vos.CinemaVO
import com.padc.csh.themovieapplication.delegates.MovieCinemaDelegate
import com.padc.csh.themovieapplication.delegates.MovieCinemaSeatConditionDelegate
import com.padc.csh.themovieapplication.viewholders.MovieCinemaViewHolder
import kotlinx.android.synthetic.main.view_holder_cinema_item.view.*

class MovieCinemaAdapter(var cinemaDelegate: MovieCinemaDelegate,var cinemaSeatConditionDelegate: MovieCinemaSeatConditionDelegate): RecyclerView.Adapter<MovieCinemaViewHolder>() {

    private var selectedPosition=-1
    private var cinemaList:List<CinemaVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCinemaViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_cinema_item,parent,false)
        return MovieCinemaViewHolder(view,cinemaDelegate)
    }

    override fun onBindViewHolder(holder: MovieCinemaViewHolder, position: Int) {

        var timeSlotAdater=MovieCinemaSeatConditionAdapter(cinemaSeatConditionDelegate)
        if(cinemaList.isNotEmpty()){
            holder.bindData(cinemaList.get(position))
            timeSlotAdater.setNewData(cinemaList.get(position).timeslots)
        }
        holder.itemView.rvMovieAvailableTime.adapter=timeSlotAdater
        holder.itemView.rvMovieAvailableTime.layoutManager=GridLayoutManager(holder.itemView.context,3)

        holder.itemView.tvSeeDetails.setOnClickListener {
            //call delegate
            cinemaDelegate.onMovieCinema(position)
        }

        when(selectedPosition){
            position->{
                holder.itemView.rvMovieAvailableTime.visibility=View.VISIBLE
                holder.itemView.ivBookingInfo.visibility=View.VISIBLE
                holder.itemView.tvBookingSeatingInfo.visibility=View.VISIBLE
            }
            else->{
                holder.itemView.rvMovieAvailableTime.visibility=View.GONE
                holder.itemView.ivBookingInfo.visibility=View.GONE
                holder.itemView.tvBookingSeatingInfo.visibility=View.GONE
            }

        }
    }

    public fun setSelectedPosition(position: Int){
        selectedPosition=position
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
       return cinemaList.size
    }

    fun setNewData(it: List<CinemaVO>) {
        cinemaList=it
        notifyDataSetChanged()
    }
}