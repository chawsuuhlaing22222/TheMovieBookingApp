package com.padc.csh.themovieapplication.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padc.csh.themovieapp.data.vos.ActorVO
import com.padc.csh.themovieapplication.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_movie_cast.view.*

class MovieCastViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
    fun bindData(actor: ActorVO) {
        Glide.with(itemView.context).load("$IMAGE_BASE_URL${actor.profile_path}")
            .into(itemView.ivCastProfileImage)
        itemView.tvCastName.text=actor.name
    }
}