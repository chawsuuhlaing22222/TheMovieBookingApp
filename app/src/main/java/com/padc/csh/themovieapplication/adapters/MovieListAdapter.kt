package com.padc.csh.themovieapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.delegates.MovieListDelegate
import com.padc.csh.themovieapplication.viewholders.MovieListViwHolder
import kotlinx.android.synthetic.main.view_holder_movie_item.view.*

class MovieListAdapter(val delegate:MovieListDelegate,val flag:String): RecyclerView.Adapter<MovieListViwHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViwHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_item,parent,false)
        when(flag){
            "now"->{view.tvCommingShowDate.visibility= View.GONE}
            "comming"->{view.tvCommingShowDate.visibility= View.VISIBLE}
            else-> {view.tvCommingShowDate.visibility= View.GONE}
        }
        return MovieListViwHolder(view,delegate,flag)
    }

    override fun onBindViewHolder(holder: MovieListViwHolder, position: Int) {

    }

    override fun getItemCount(): Int {
       return 12
    }
}