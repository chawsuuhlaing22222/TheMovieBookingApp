package com.padc.csh.themovieapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.delegates.ChildSeatDelegate
import com.padc.csh.themovieapplication.delegates.SeatPlanDelegate
import com.padc.csh.themovieapplication.viewholders.SeatPlanViewHolder
import kotlinx.android.synthetic.main.view_holder_seat_plan.view.*

class SeatPlanAdapter(var context: Context,var delegate: SeatPlanDelegate,var childDelegate: ChildSeatDelegate): RecyclerView.Adapter<SeatPlanViewHolder>() {
    var mChildSeatAdapter=ChildSeatAdapter(childDelegate)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatPlanViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.activity_cinema_seat_plan,parent,false)
        return SeatPlanViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: SeatPlanViewHolder, position: Int) {

        holder.itemView.rvSeatList.adapter=mChildSeatAdapter
        holder.itemView.rvSeatList.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

    }

    override fun getItemCount(): Int {
     return 5
    }
}