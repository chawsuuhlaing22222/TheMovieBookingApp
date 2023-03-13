package com.padc.csh.themovieapplication.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padc.csh.themovieapplication.data.vos.SnackVO
import com.padc.csh.themovieapplication.delegates.SnackItemDelegate
import kotlinx.android.synthetic.main.view_holder_snack_item.view.*

class SnackItemViewHolder(itemView: View, delegate: SnackItemDelegate) :
    RecyclerView.ViewHolder(itemView) {
    fun bindData(snack: SnackVO) {
        Glide.with(itemView.context).load(snack.image).into(itemView.ivSnack)
        itemView.tvSnackName.text = snack.name
        itemView.tvSnackPrice.text = snack.price.toString()

        if (snack.count==0 || snack.count==null) {
            itemView.llAdditionalPlusMinusFood.visibility = View.GONE
            itemView.btnAdd.visibility = View.VISIBLE
        } else {
            itemView.llAdditionalPlusMinusFood.visibility = View.VISIBLE
            itemView.btnAdd.visibility = View.GONE
            itemView.tvOrderFoodCount.text=snack.count.toString()
        }
    }
}
