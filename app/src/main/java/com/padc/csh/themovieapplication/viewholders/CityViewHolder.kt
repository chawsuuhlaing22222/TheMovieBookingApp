package com.padc.csh.themovieapplication.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.delegates.CityDelegate

class CityViewHolder(itemView: View,var delegate: CityDelegate) :RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            delegate.onCityItemClick()
        }
    }
}
