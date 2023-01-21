package com.padc.csh.themovieapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.delegates.CityDelegate
import com.padc.csh.themovieapplication.dummy.cityList
import com.padc.csh.themovieapplication.viewholders.CityViewHolder
import kotlinx.android.synthetic.main.activity_choose_location.view.*
import kotlinx.android.synthetic.main.view_item_custom_spinner_dropdown.view.*

class CityAdapter(var delegate: CityDelegate): RecyclerView.Adapter<CityViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_item_custom_spinner_dropdown,parent,false)
        return CityViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
       holder.itemView.tvSpinnerSelectedValue.text= cityList.get(position)
    }

    override fun getItemCount(): Int {
      return cityList.size
    }
}