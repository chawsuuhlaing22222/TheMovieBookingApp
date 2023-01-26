package com.padc.csh.themovieapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.delegates.ChildSeatDelegate
import com.padc.csh.themovieapplication.viewholders.ChildSeatViewHolder

class ChildSeatAdapter(var delegate: ChildSeatDelegate):RecyclerView.Adapter<ChildSeatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildSeatViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_seat,parent,false)
        return ChildSeatViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: ChildSeatViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
      return 12
    }
}