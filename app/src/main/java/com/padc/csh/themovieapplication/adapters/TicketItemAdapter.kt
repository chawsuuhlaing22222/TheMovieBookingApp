package com.padc.csh.themovieapplication.adapters

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.data.vos.TicketVO
import com.padc.csh.themovieapplication.delegates.TicketItemDelegate
import com.padc.csh.themovieapplication.viewholders.TicketItemViewHolder
import kotlinx.android.synthetic.main.activity_ticket_confirmation.*
import kotlinx.android.synthetic.main.view_holder_ticket_item.view.*

class TicketItemAdapter(var context: Context,var delegate: TicketItemDelegate):RecyclerView.Adapter<TicketItemViewHolder>() {

    private var mTicketList = listOf<TicketVO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketItemViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.view_holder_ticket_item,parent,false)
        return TicketItemViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: TicketItemViewHolder, position: Int) {

        var ticketVO=mTicketList.get(position)
        holder.itemView.tvMovieNameTicketScrn.text= Html.fromHtml("<font color='#ffffff'><b> ${ticketVO.movieName}</b> </font><font color='#888888'>(3D)(U/A)</font>")
        holder.itemView.tvTicketTypeTicketScrn.setText(Html.fromHtml("<font color='#ffffff'><b>${ticketVO.seatNameList}</b></font><font color='#888888'>(SCREEN2)</font>"))
        holder.itemView.tvTicketCountTicketScrn.text= Html.fromHtml("<font color='#888888'>M-Ticket (</font><font color='#00ff6a'>${ticketVO.seatCount}</font><font color='#888888'>)</font>")

        holder.itemView.tvMovieDateTicketScrn.text=ticketVO.bookingDate
        holder.itemView.tvMovieShowTimeTicketScrn.text=ticketVO.startTime

        holder.itemView.setOnClickListener {
            delegate.onTapTicketItem()
        }
    }

    override fun getItemCount(): Int {
       return mTicketList.size
    }

    fun setNewData(it: List<TicketVO>) {
        mTicketList = it
    }
}