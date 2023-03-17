package com.padc.csh.themovieapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.data.vos.AllCinemaVO
import com.padc.csh.themovieapplication.delegates.MovieCinemaDelegate
import com.padc.csh.themovieapplication.viewholders.AllCinemaListViewHolder
import kotlinx.android.synthetic.main.view_holder_all_cinema_fragment.view.*

class AllCinemaListAdapter(var delegate: MovieCinemaDelegate):
    RecyclerView.Adapter<AllCinemaListViewHolder>() {

    private var cinemaList:List<AllCinemaVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllCinemaListViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_all_cinema_fragment,parent,false)
        return AllCinemaListViewHolder(view, delegate =delegate )
    }

    override fun onBindViewHolder(holder: AllCinemaListViewHolder, position: Int) {

        cinemaList?.let {
            var cinema=it.get(position)
            holder.itemView.tvCinemaNameAtFrag.text=cinema.name

            holder.itemView.tvSeeDetailsAtFrag.setOnClickListener {
                delegate.onMovieCinemaAtFrag(cinema)
            }
        }


    }

    override fun getItemCount(): Int {
       return cinemaList.size
    }

    fun setNewData(newCinemaList:List<AllCinemaVO>){
        cinemaList=newCinemaList
        notifyDataSetChanged()
    }
}