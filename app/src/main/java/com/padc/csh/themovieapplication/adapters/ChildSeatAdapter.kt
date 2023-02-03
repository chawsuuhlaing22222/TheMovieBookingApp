package com.padc.csh.themovieapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.delegates.ChildSeatDelegate
import com.padc.csh.themovieapplication.dummy.ChildSeat
import com.padc.csh.themovieapplication.viewholders.ChildSeatViewHolder
import kotlinx.android.synthetic.main.view_holder_seat.view.*


class ChildSeatAdapter(var delegate: ChildSeatDelegate):RecyclerView.Adapter<ChildSeatViewHolder>() {

    var childSeat:ChildSeat? = null
    var TYPE_SPACE=3
    var TYPE_LABEL=4
    var TYPE_SEAT_ONE=1
    var TYPE_SEAT_TWO=2
    var TYPE_SMALL=5
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildSeatViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_seat,parent,false)
        return ChildSeatViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: ChildSeatViewHolder, position: Int) {

        holder.itemView.ivSeatImg.setOnClickListener {
            when(getItemViewType(position)){
                TYPE_SEAT_ONE->{
                    holder.itemView.ivSeatImg.setColorFilter(ContextCompat.getColor(holder.itemView.context, R.color.colorAccent), android.graphics.PorterDuff.Mode.MULTIPLY);
                    //holder.itemView.ivSeatImg.setImageDrawable(null)
                    //holder.itemView.ivSeatImg.setImageResource(R.drawable.ic_your_selection_single_chair)
                }
                TYPE_SEAT_TWO->{
                    holder.itemView.ivSeatImg.setColorFilter(ContextCompat.getColor(holder.itemView.context, R.color.colorAccent), android.graphics.PorterDuff.Mode.MULTIPLY);
                    //holder.itemView.ivSeatImg.setImageDrawable(null)
                   // holder.itemView.ivSeatImg.setImageResource(R.drawable.ic_taken_couple_seat)
                }
            }



        }

        when(getItemViewType(position)){

            TYPE_LABEL->{
                holder.itemView.tvSeatLevel.visibility=View.VISIBLE
                holder.itemView.tvSeatLevel.text=childSeat?.childSeatList?.get(position)

                holder.itemView.ivSeatImg.visibility=View.GONE
                holder.itemView.viewSpace.visibility=View.GONE

            }

            TYPE_SPACE->{
                holder.itemView.tvSeatLevel.visibility=View.GONE
                holder.itemView.ivSeatImg.visibility=View.GONE
                holder.itemView.viewSpace.visibility=View.VISIBLE

            }

            TYPE_SEAT_ONE->{
                holder.itemView.ivSeatImg.visibility=View.VISIBLE
                holder.itemView.ivSeatImg.setImageResource(R.drawable.ic_seat_one)
                holder.itemView.tvSeatLevel.visibility=View.GONE
                holder.itemView.viewSpace.visibility=View.GONE

            }
            TYPE_SEAT_TWO->{
                holder.itemView.ivSeatImg.visibility=View.VISIBLE
                holder.itemView.ivSeatImg.setImageDrawable(null)
                holder.itemView.ivSeatImg.setImageResource(R.drawable.ic_available_couplte_seat)
                holder.itemView.tvSeatLevel.visibility=View.GONE
                holder.itemView.viewSpace.visibility=View.GONE

            }

        }

    }

    override fun getItemCount(): Int {
      return childSeat?.childSeatList?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return when(childSeat?.childSeatList?.get(position)){
            "one"->{
                TYPE_SEAT_ONE
            }

            "two"->{
                TYPE_SEAT_TWO
            }
            "space"->{
                TYPE_SPACE
            }

            else->{
                TYPE_LABEL
            }
        }
    }

    public fun setUpData(newChildSeat: ChildSeat){
        childSeat=newChildSeat
       // notifyDataSetChanged()
    }
}