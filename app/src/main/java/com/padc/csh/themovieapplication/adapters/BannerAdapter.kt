package com.padc.csh.themovieapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padc.csh.themovieapplication.delegates.BannerDelegate
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.data.vos.BannerVO
import com.padc.csh.themovieapplication.viewholders.BannerViewHolder
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.view_holder_banner.view.*

class BannerAdapter(val delegate: BannerDelegate): RecyclerView.Adapter<BannerViewHolder>() {

    private var bannerList:List<BannerVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_banner,parent,false)
        return BannerViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {

          bannerList?.let {
              var movie=bannerList.get(position)
              Glide.with(holder.itemView.context).load(movie.url)
                  .into(  holder.itemView.ivBanner)

              holder.itemView.setOnClickListener {
                  delegate.onTapBanner(movie)
              }

          }
    }

    override fun getItemCount(): Int {
        return bannerList.size
    }

    fun setNewData(it: List<BannerVO>) {
        bannerList=it
        notifyDataSetChanged()
    }
}