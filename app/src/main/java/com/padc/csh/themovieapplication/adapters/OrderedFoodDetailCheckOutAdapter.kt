package com.padc.csh.themovieapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.data.vos.SnackVO
import com.padc.csh.themovieapplication.delegates.OrderFoolListChekoutDelegate
import com.padc.csh.themovieapplication.dummy.orderedFoodList
import com.padc.csh.themovieapplication.viewholders.OrderedFoodDetailCheckOutViewHolder
import com.padc.csh.themovieapplication.viewholders.OrderedFoodDetailViewHolder
import kotlinx.android.synthetic.main.view_holder_order_food_list_checkout_scrn.view.*

class OrderedFoodDetailCheckOutAdapter(var delegate: OrderFoolListChekoutDelegate):
    RecyclerView.Adapter<OrderedFoodDetailCheckOutViewHolder>() {

    private var data:List<SnackVO> = listOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderedFoodDetailCheckOutViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_order_food_list_checkout_scrn,parent,false)
        return OrderedFoodDetailCheckOutViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: OrderedFoodDetailCheckOutViewHolder, position: Int) {

        var snack=data.get(position)
        holder.itemView.tvFoodNameCheckoutScrnVH.text= snack.name
        holder.itemView.tvFoodPriceOrderedDetailVH.text=holder.itemView.context.getString(R.string.order_snack_price,snack.price.times(snack.count).toString())
        holder.itemView.btnCancelFoodOrderedCheckoutScrn.setOnClickListener {
            delegate.onFoodCancel(snack)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setNewData(snackList: List<SnackVO>?) {
        if (snackList != null) {
            data=snackList
        }
        notifyDataSetChanged()
    }

}