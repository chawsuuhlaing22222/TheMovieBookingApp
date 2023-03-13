package com.padc.csh.themovieapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.data.vos.SnackVO
import com.padc.csh.themovieapplication.delegates.SnackItemDelegate
import com.padc.csh.themovieapplication.viewholders.SnackItemViewHolder

class SnackListAdapter(var delegate: SnackItemDelegate): RecyclerView.Adapter<SnackItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnackItemViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_snack_item,parent,false)
        return SnackItemViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: SnackItemViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }

    fun setNewData(it: List<SnackVO>) {

    }
}