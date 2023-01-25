package com.padc.csh.themovieapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.delegates.OrderFoolListChekoutDelegate
import com.padc.csh.themovieapplication.dummy.orderedFoodList
import com.padc.csh.themovieapplication.viewholders.OrderedFoodDetailCheckOutViewHolder
import com.padc.csh.themovieapplication.viewholders.OrderedFoodDetailViewHolder
import kotlinx.android.synthetic.main.view_holder_order_food_list_checkout_scrn.view.*

class OrderedFoodDetailCheckOutAdapter(var delegate: OrderFoolListChekoutDelegate):
    RecyclerView.Adapter<OrderedFoodDetailCheckOutViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderedFoodDetailCheckOutViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_order_food_list_checkout_scrn,parent,false)
        return OrderedFoodDetailCheckOutViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: OrderedFoodDetailCheckOutViewHolder, position: Int) {
        holder.itemView.tvFoodNameCheckoutScrnVH.text= orderedFoodList.get(position)
        holder.itemView.btnCancelFoodOrderedCheckoutScrn.setOnClickListener {
            delegate.onFoodCancel(position)
        }
    }

    override fun getItemCount(): Int {
        return 3
    }

}