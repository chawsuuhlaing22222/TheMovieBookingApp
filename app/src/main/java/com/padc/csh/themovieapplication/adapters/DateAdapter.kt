package com.padc.csh.themovieapplication.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.delegates.DateDelegate
import com.padc.csh.themovieapplication.viewholders.DateViewHolder
import com.tbuonomo.viewpagerdotsindicator.setBackgroundCompat
import kotlinx.android.synthetic.main.view_holder_date_item.view.*
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

class DateAdapter(var context: Context,var delegate: DateDelegate): RecyclerView.Adapter<DateViewHolder>() {
var selectedPosition=0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_date_item,parent,false)
        return DateViewHolder(view,delegate)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        //val date=dateList.get(position)
        val date= LocalDate.now().plusDays(position.toLong())
        when(position){
            0->{
                holder.itemView.tvDayName.text="Today"

            }
            1->{
                holder.itemView.tvDayName.text="Tomorrow"
                holder.itemView.tvDayName.setTextSize(12.0f)
            }
            else->{
                holder.itemView.tvDayName.text=date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("en"))
            }
        }

        holder.itemView.tvDayValue.text= date.dayOfMonth.toString()
        holder.itemView.tvMonthName.text= date.month.getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("en"))

        if(selectedPosition==position){
            holder.itemView.background=holder.itemView.context.getDrawable(R.drawable.layer_list_active_date_card_bg)
        }else{
            holder.itemView.background=holder.itemView.context.getDrawable(R.drawable.layer_list_inactive_date_card)
        }

        holder.itemView.setOnClickListener {
            delegate.onDateCardClick(position)
        }

    }

    override fun getItemCount(): Int {
     return 7

    }

    public fun setSelectedDatePosition(position: Int){
        selectedPosition=position
        notifyDataSetChanged()
    }
}