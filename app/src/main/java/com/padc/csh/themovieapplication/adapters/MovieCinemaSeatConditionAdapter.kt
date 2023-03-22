package com.padc.csh.themovieapplication.adapters

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.activities.CinemaTimeSlotActivity
import com.padc.csh.themovieapplication.data.models.MovieBookingModel
import com.padc.csh.themovieapplication.data.models.MovieBookingModelImpl
import com.padc.csh.themovieapplication.data.vos.CinemaTimeSlotVO
import com.padc.csh.themovieapplication.delegates.MovieCinemaDelegate
import com.padc.csh.themovieapplication.delegates.MovieCinemaSeatConditionDelegate
import com.padc.csh.themovieapplication.utils.ColorTransparentUtils
import com.padc.csh.themovieapplication.viewholders.MovieCinemaSeatConditionViewHolder
import com.padc.csh.themovieapplication.viewholders.MovieCinemaViewHolder
import kotlinx.android.synthetic.main.activity_cinema_search.view.*
import kotlinx.android.synthetic.main.view_holder_cinema_seat_condition.view.*

class MovieCinemaSeatConditionAdapter(var movieCinemaSeatConditionDelegate: MovieCinemaSeatConditionDelegate) :
    RecyclerView.Adapter<MovieCinemaSeatConditionViewHolder>() {

    var cinemaTimeSlots: List<CinemaTimeSlotVO> = listOf()
    private var mTheMovieBookingModel : MovieBookingModel =MovieBookingModelImpl
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieCinemaSeatConditionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_cinema_seat_condition, parent, false)
        return MovieCinemaSeatConditionViewHolder(view, movieCinemaSeatConditionDelegate)
    }

    override fun getItemCount(): Int {
        return cinemaTimeSlots.size
    }

    override fun onBindViewHolder(holder: MovieCinemaSeatConditionViewHolder, position: Int) {


        if (cinemaTimeSlots.isNotEmpty()) {
            var timeslot = cinemaTimeSlots.get(position)
            holder.itemView.tvAvailableTime.text = timeslot.start_time

            var color = timeslot.status?.let { getColor(it) }
            val newShape = GradientDrawable()
            newShape.shape = GradientDrawable.RECTANGLE
            newShape.cornerRadius = holder . itemView . resources . getDimension (R.dimen.margin_small)
            newShape . setStroke (1, Color.parseColor(color))
            newShape.setColor( Color.parseColor(ColorTransparentUtils.transparentColor(Color.parseColor(color),10)))
            holder.itemView.background = newShape

                    holder.itemView.setOnClickListener {
                        movieCinemaSeatConditionDelegate.onMovieCinemaSeatPlanClick(timeslot)
                    }
//            when(timeslot.status){
//                1->{
//                    holder.itemView.setBackgroundResource(R.drawable.bg_available_cinema_show_time)
//                }
//                2->{
//                    holder.itemView.setBackgroundResource(R.drawable.bg_fillingfast_cinema_show_time)
//
//                }
//                3->{
//                    holder.itemView.setBackgroundResource(R.drawable.bg_almostfull_cinema_show_time)
//                }
//            }


        }


    }

    fun setNewData(timeslots: List<CinemaTimeSlotVO>?) {
        cinemaTimeSlots = timeslots ?: listOf()
        notifyDataSetChanged()
    }

    fun getColor(status: Int): String {
        var color = ""
        mTheMovieBookingModel.getTimeSlotColor(status) {
            color = it.color.toString()
            // Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
        }
        return color
//        var color = ""
//        if (CinemaTimeSlotActivity?.timeSlotList?.size != 0) {
//            for (timeslot in CinemaTimeSlotActivity?.timeSlotList ?: listOf()) {
//                if (timeslot.id?.equals(status) == true) {
//                    color = timeslot.color.toString()
//
//                }
//            }
//        }
//        return color
    }
}