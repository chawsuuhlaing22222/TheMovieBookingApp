package com.padc.csh.themovieapplication.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.delegates.BannerDelegate

class BannerViewHolder(itemView: View,delegate: BannerDelegate) :RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            delegate.onTapBanner()
        }
    }
}