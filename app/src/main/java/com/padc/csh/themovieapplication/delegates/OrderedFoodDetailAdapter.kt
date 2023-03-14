package com.padc.csh.themovieapplication.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.data.vos.SnackVO
import com.padc.csh.themovieapplication.viewholders.OrderedFoodDetailViewHolder
import kotlinx.android.synthetic.main.view_holder_ordered_food_detail.view.*
import kotlinx.android.synthetic.main.view_holder_snack_item.view.*

class OrderedFoodDetailAdapter(var delegate:SnackItemDelegate): RecyclerView.Adapter<OrderedFoodDetailViewHolder>() {

    private var orderSnackList:List<SnackVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderedFoodDetailViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_ordered_food_detail,parent,false)
        return OrderedFoodDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderedFoodDetailViewHolder, position: Int) {
        orderSnackList?.let {
            var snackVO=orderSnackList.get(position)
            holder.bindData(snackVO)
            actionListener(holder,snackVO)


        }
    }

    private fun actionListener(holder: OrderedFoodDetailViewHolder, snackVO: SnackVO) {
        holder.itemView.btnPlusFoodOrderedDetailVH.setOnClickListener {
            snackVO.count= snackVO.count.plus(1)
            holder.itemView.tvOrderFoodCountOrderedDetailVH.text = snackVO.count.toString()
            delegate.updateSnack(snackVO)
        }

        holder.itemView.btnMinusFoodOrderedDetailVH.setOnClickListener {
            snackVO.count=snackVO.count.minus(1)
            holder.itemView.tvOrderFoodCountOrderedDetailVH.text = snackVO.count.toString()
            delegate.updateSnack(snackVO)
        }
    }

    override fun getItemCount(): Int {
       return orderSnackList.size
    }

    fun setNewData(newOrderSnackList: MutableList<SnackVO>) {
        orderSnackList = newOrderSnackList
        notifyDataSetChanged()
    }
}