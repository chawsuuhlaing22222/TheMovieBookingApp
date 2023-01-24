package com.padc.csh.themovieapplication.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.viewholders.OrderedFoodDetailViewHolder

class OrderedFoodDetailAdapter: RecyclerView.Adapter<OrderedFoodDetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderedFoodDetailViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_ordered_food_detail,parent,false)
        return OrderedFoodDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderedFoodDetailViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
       return 5
    }
}