package com.padc.csh.themovieapplication.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.viewholders.DateViewHolder
import com.tbuonomo.viewpagerdotsindicator.setBackgroundCompat
import kotlinx.android.synthetic.main.view_holder_date_item.view.*
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

class DateAdapter: RecyclerView.Adapter<DateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_date_item,parent,false)
        return DateViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        //val date=dateList.get(position)
        val date= LocalDate.now().plusDays(position.toLong())
        when(position){
            0->{
                holder.itemView.tvDayName.text="Today"
                holder.itemView.background=holder.itemView.context.getDrawable(R.drawable.bg_active_calendar)
            }
            1->{
                holder.itemView.tvDayName.text="Tomorrow"
            }
            else->{
                holder.itemView.tvDayName.text=date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("en"))
            }
        }

        holder.itemView.tvDayValue.text= date.dayOfMonth.toString()
        holder.itemView.tvMonthName.text= date.month.getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("en"))
    }

    override fun getItemCount(): Int {
     return 7

    }
}