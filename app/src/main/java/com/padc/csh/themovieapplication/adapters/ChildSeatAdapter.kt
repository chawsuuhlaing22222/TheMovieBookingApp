package com.padc.csh.themovieapplication.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.data.vos.SeatListVO
import com.padc.csh.themovieapplication.data.vos.SeatVO
import com.padc.csh.themovieapplication.delegates.ChildSeatDelegate
import com.padc.csh.themovieapplication.dummy.ChildSeat
import com.padc.csh.themovieapplication.viewholders.ChildSeatViewHolder
import kotlinx.android.synthetic.main.view_holder_seat.view.*


class ChildSeatAdapter(var delegate: ChildSeatDelegate):RecyclerView.Adapter<ChildSeatViewHolder>() {

    var seatList:List<List<SeatVO>> = listOf()
    var countInOneRow=0
    var TYPE_SPACE=5
    var TYPE_LABEL=4
    var TYPE_SEAT_AVALIABLE=1
    var TYPE_SEAT_TAKEN=2
    var TYPE_SEAT_SELECTION = 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildSeatViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_seat,parent,false)
        return ChildSeatViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: ChildSeatViewHolder, position: Int) {

       var seat=getSeat(position)
        //action
        holder.itemView.ivSeatImg.setOnClickListener {

            when(getItemViewType(position)){
                TYPE_SEAT_AVALIABLE->{
                    seat?.type="selection"
                    notifyDataSetChanged()


                }

                TYPE_SEAT_SELECTION->{
                    seat?.type="available"
                    notifyDataSetChanged()

                }

            }
        }

        when(getItemViewType(position)){

            TYPE_LABEL->{
                holder.itemView.tvSeatLevel.visibility=View.VISIBLE
                holder.itemView.tvSeatLevel.text=seat?.symbol
                holder.itemView.ivSeatImg.visibility=View.GONE
                holder.itemView.viewSpace.visibility=View.GONE

            }

            TYPE_SPACE->{
                holder.itemView.tvSeatLevel.visibility=View.GONE
                holder.itemView.ivSeatImg.visibility=View.GONE
                holder.itemView.viewSpace.visibility=View.VISIBLE

            }

            TYPE_SEAT_AVALIABLE->{
                holder.itemView.ivSeatImg.visibility=View.VISIBLE
                holder.itemView.ivSeatImg.setImageResource(R.drawable.ic_white_chair)
                holder.itemView.tvSeatLevel.visibility=View.GONE
                holder.itemView.viewSpace.visibility=View.GONE

            }
            TYPE_SEAT_SELECTION->{
                holder.itemView.ivSeatImg.visibility=View.VISIBLE
                holder.itemView.ivSeatImg.setImageResource(R.drawable.ic_white_chair)
                holder.itemView.ivSeatImg.setColorFilter(ContextCompat.getColor(holder.itemView.context, R.color.colorAccent), android.graphics.PorterDuff.Mode.MULTIPLY)
                holder.itemView.tvSeatLevel.visibility=View.GONE
                holder.itemView.viewSpace.visibility=View.GONE

            }

        }

    }

    override fun getItemCount(): Int {
        var count=(seatList.size).times(countInOneRow)
         return count
    }

    override fun getItemViewType(position: Int): Int {
                var seat=getSeat(position)
              return  when(seat?.type){
                "available"->{
                    TYPE_SEAT_AVALIABLE
                }

                "taken"->{
                    TYPE_SEAT_TAKEN
                }
                "space"->{
                    TYPE_SPACE
                }
                "text"->{
                    TYPE_LABEL
                }
                "selection"->{
                    TYPE_SEAT_SELECTION
                }
                  else->{
                      TYPE_LABEL
                  }

        }

    }
    @SuppressLint("NotifyDataSetChanged")
    public fun setUpData(data:List<List<SeatVO>>){
        seatList=data
        countInOneRow=seatList.get(0).count()
        notifyDataSetChanged()
    }

    fun getSeat(position: Int):SeatVO?{
        var row=position / countInOneRow
        var col=position % countInOneRow
        var seat=seatList.get(row).get(col)
        return seat
    }
}