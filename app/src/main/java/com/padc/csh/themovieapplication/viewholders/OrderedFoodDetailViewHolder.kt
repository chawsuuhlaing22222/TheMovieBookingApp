package com.padc.csh.themovieapplication.viewholders

import android.provider.Settings.Global.getString
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.data.vos.SnackVO
import kotlinx.android.synthetic.main.view_holder_ordered_food_detail.view.*
import kotlinx.android.synthetic.main.view_holder_snack_item.view.*

class OrderedFoodDetailViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
    fun bindData(snack: SnackVO) {
            itemView.tvOrderFoodCountOrderedDetailVH.text=snack.count.toString()
        itemView.tvFoodNameOrderedDetailVH.text=snack.name
        itemView.tvFoodPriceOrderedDetailVH.text=itemView.context.getString(R.string.order_snack_price,snack.price.toString())
    }
}