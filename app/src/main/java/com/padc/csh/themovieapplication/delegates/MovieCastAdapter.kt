package com.padc.csh.themovieapplication.delegates

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapp.data.vos.ActorVO
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.viewholders.MovieCastViewHolder

class MovieCastAdapter: RecyclerView.Adapter<MovieCastViewHolder>() {

    private var actorList:List<ActorVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCastViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_cast,parent,false)
        return MovieCastViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieCastViewHolder, position: Int) {

        if(actorList.isNotEmpty()){
            holder.bindData(actorList.get(position))
        }
    }

    override fun getItemCount(): Int {
       return actorList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(it: List<ActorVO>) {
        actorList=it
        notifyDataSetChanged()
    }
}