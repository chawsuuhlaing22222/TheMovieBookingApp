package com.padc.csh.themovieapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.delegates.BannerDelegate
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.viewholders.BannerViewHolder

class BannerAdapter(val delegate: BannerDelegate): RecyclerView.Adapter<BannerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_banner,parent,false)
        return BannerViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 5
    }
}