package com.padc.csh.themovieapplication.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.delegates.MovieListDelegate
import com.padc.csh.themovieapplication.viewholders.NowShowingMovieListViewHolder

class NowShowingMovieAdapter(var delegate: MovieListDelegate): RecyclerView.Adapter<NowShowingMovieListViewHolder>() {
   private var nowPlayingMovieList:List<MovieVO> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NowShowingMovieListViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_item,parent,false)
        return NowShowingMovieListViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: NowShowingMovieListViewHolder, position: Int) {

        var movieVO:MovieVO?=null
        if(nowPlayingMovieList.isNotEmpty()){

            movieVO=nowPlayingMovieList.get(position)
            holder.bindData(movieVO)
        }

        holder.itemView.setOnClickListener {
            movieVO?.let {
                delegate.onTapNowShowingMovie(it.id.toString())
            }

        }


    }

    override fun getItemCount(): Int {
       return nowPlayingMovieList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movieList:List<MovieVO>){
        nowPlayingMovieList=movieList
        notifyDataSetChanged()
    }
}