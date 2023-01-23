package com.padc.csh.themovieapplication.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.delegates.SnackItemDelegate
import kotlinx.android.synthetic.main.view_holder_snack_item.view.*

class SnackItemViewHolder(itemView: View,delegate: SnackItemDelegate) :RecyclerView.ViewHolder(itemView) {

    init {
        itemView.btnAdd.setOnClickListener {

        }
    }
}