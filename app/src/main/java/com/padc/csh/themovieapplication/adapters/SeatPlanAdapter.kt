package com.padc.csh.themovieapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.delegates.ChildSeatDelegate
import com.padc.csh.themovieapplication.delegates.SeatPlanDelegate
import com.padc.csh.themovieapplication.dummy.SeatPlan
import com.padc.csh.themovieapplication.dummy.seatPlanlist
import com.padc.csh.themovieapplication.viewholders.SeatPlanViewHolder
import kotlinx.android.synthetic.main.view_holder_seat_plan.view.*

class SeatPlanAdapter(var context: Context,var delegate: SeatPlanDelegate,var childDelegate: ChildSeatDelegate): RecyclerView.Adapter<SeatPlanViewHolder>() {
    //var mChildSeatAdapter=ChildSeatAdapter(childDelegate)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatPlanViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_seat_plan,parent,false)
        return SeatPlanViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: SeatPlanViewHolder, position: Int) {
        var mChildSeatAdapter=ChildSeatAdapter(childDelegate)
        holder.itemView.tvSeatPricelbl.text = seatPlanlist.get(position).price
        holder.itemView.rvSeatList.adapter=mChildSeatAdapter
       // mChildSeatAdapter.setUpData(seatPlanlist.get(position).childSeat)

        if(position== seatPlanlist.size-1){
            holder.itemView.rvSeatList.layoutManager=GridLayoutManager(context,11)
        }else{
            holder.itemView.rvSeatList.layoutManager=GridLayoutManager(context,12)
        }



    }

    override fun getItemCount(): Int {
     return seatPlanlist.size
    }
}