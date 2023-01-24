package com.padc.csh.themovieapplication.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.delegates.SnackItemDelegate
import kotlinx.android.synthetic.main.view_holder_snack_item.view.*

class SnackItemViewHolder(itemView: View,delegate: SnackItemDelegate) :RecyclerView.ViewHolder(itemView) {

    init {
        itemView.btnAdd.setOnClickListener {
          itemView.btnAdd.visibility=View.GONE
            itemView.llAdditionalPlusMinusFood.visibility=View.VISIBLE
        }

        itemView.btnPlusFood.setOnClickListener {
            var currentCount=itemView.tvOrderFoodCount.text.toString().toInt()
            itemView.tvOrderFoodCount.text=(currentCount+1).toString()
        }

        itemView.btnMinusFood.setOnClickListener {
            var currentCount=itemView.tvOrderFoodCount.text.toString().toInt()
            itemView.tvOrderFoodCount.text=(currentCount-1).toString()
            if(itemView.tvOrderFoodCount.text.toString().toInt()==0){
                itemView.llAdditionalPlusMinusFood.visibility=View.GONE
                itemView.btnAdd.visibility=View.VISIBLE
            }
        }
    }
}