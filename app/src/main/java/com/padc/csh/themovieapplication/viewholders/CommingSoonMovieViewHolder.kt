package com.padc.csh.themovieapplication.viewholders

import android.text.format.DateFormat
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapplication.delegates.MovieListDelegate
import com.padc.csh.themovieapplication.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_movie_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class CommingSoonMovieViewHolder(itemView: View,delegate: MovieListDelegate) :RecyclerView.ViewHolder(itemView) {

    fun bindData(movieVO: MovieVO) {
        Glide.with(itemView.context).load("$IMAGE_BASE_URL${movieVO.posterPath}").into(itemView.ivMovie)
        itemView.tvMovieName.text=movieVO.originalTtitle

        val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val date = formattedDate.parse(movieVO.releaseDate)
        val myString = date.toString()

       var formattedDateSt=SimpleDateFormat("d'st', MMM")
        var formattedDateNd=SimpleDateFormat("d'nd', MMM")
        var formattedDateRd=SimpleDateFormat("d'rd', MMM")
        var formattedDateTH=SimpleDateFormat("d'th', MMM")

        myString?.let {

            if (it.endsWith("01") && !it.endsWith("11"))
            {
                itemView.tvCommingShowDate.text=formattedDateSt.format(date).toString()

            }else if(it.endsWith("02") && !it.endsWith("12")){
                itemView.tvCommingShowDate.text=formattedDateNd.format(date).toString()

            }else if(it.endsWith("03") && !it.endsWith("13")){
                itemView.tvCommingShowDate.text=formattedDateRd.format(date).toString()
            }else{
                itemView.tvCommingShowDate.text=formattedDateTH.format(date).toString()
            }
        }


    }


}