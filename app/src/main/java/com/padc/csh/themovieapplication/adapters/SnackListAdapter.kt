package com.padc.csh.themovieapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.data.vos.SnackVO
import com.padc.csh.themovieapplication.delegates.SnackItemDelegate
import com.padc.csh.themovieapplication.viewholders.SnackItemViewHolder
import kotlinx.android.synthetic.main.view_holder_snack_item.view.*

class SnackListAdapter(var delegate: SnackItemDelegate) :
    RecyclerView.Adapter<SnackItemViewHolder>() {

    var snackList: List<SnackVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnackItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_snack_item, parent, false)
        return SnackItemViewHolder(view, delegate)
    }

    override fun onBindViewHolder(holder: SnackItemViewHolder, position: Int) {
        var snack = snackList.get(position)
        holder.bindData(snack)
        actionListener(holder,snack)
    }

    override fun getItemCount(): Int {
        return snackList.size
    }

    fun setNewData(it: List<SnackVO>) {
        snackList = it
        notifyDataSetChanged()
    }

    fun actionListener(holder: SnackItemViewHolder,snackVO: SnackVO){

        holder.itemView.btnAdd.setOnClickListener {
            holder.itemView.btnAdd.visibility = View.GONE
            holder.itemView.llAdditionalPlusMinusFood.visibility = View.VISIBLE
        }

        holder.itemView.btnPlusFood.setOnClickListener {
            snackVO.count= snackVO.count.plus(1)
            holder.itemView.tvOrderFoodCount.text = snackVO.count.toString()
            delegate.updateSnack(snackVO)
        }

        holder.itemView.btnMinusFood.setOnClickListener {
            //var currentCount = holder.itemView.tvOrderFoodCount.text.toString().toInt()
            snackVO.count=snackVO.count.minus(1)
            holder.itemView.tvOrderFoodCount.text = snackVO.count.toString()
            if (snackVO.count == 0) {
                holder.itemView.llAdditionalPlusMinusFood.visibility = View.GONE
                holder.itemView.btnAdd.visibility = View.VISIBLE
            }
            delegate.updateSnack(snackVO)
        }
    }
}