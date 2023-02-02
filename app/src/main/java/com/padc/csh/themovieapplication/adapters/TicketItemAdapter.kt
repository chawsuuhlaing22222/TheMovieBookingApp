package com.padc.csh.themovieapplication.adapters

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.delegates.TicketItemDelegate
import com.padc.csh.themovieapplication.viewholders.TicketItemViewHolder
import kotlinx.android.synthetic.main.activity_ticket_confirmation.*
import kotlinx.android.synthetic.main.view_holder_ticket_item.view.*

class TicketItemAdapter(var context: Context,var delegate: TicketItemDelegate):RecyclerView.Adapter<TicketItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketItemViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.view_holder_ticket_item,parent,false)
        return TicketItemViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: TicketItemViewHolder, position: Int) {

        holder.itemView.tvMovieNameTicketScrn.text= Html.fromHtml("<font color='#ffffff'><b>Black White</b> </font><font color='#888888'>(3D)(U/A)</font>")
        holder.itemView.tvTicketTypeTicketScrn.setText(Html.fromHtml("<font color='#ffffff'><b>Gold-G8,G7</b></font><font color='#888888'>(SCREEN2)</font>"))
        holder.itemView.tvTicketCountTicketScrn.text= Html.fromHtml("<font color='#888888'>M-Ticket (</font><font color='#00ff6a'>2</font><font color='#888888'>)</font>")

        holder.itemView.setOnClickListener {
            delegate.onTapTicketItem()
        }
    }

    override fun getItemCount(): Int {
       return 2
    }
}