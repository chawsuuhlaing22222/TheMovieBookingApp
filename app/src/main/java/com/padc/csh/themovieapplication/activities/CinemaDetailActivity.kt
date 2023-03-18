package com.padc.csh.themovieapplication.activities

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebSettings.ZoomDensity
import android.widget.MediaController
import androidx.constraintlayout.widget.ConstraintSet.Layout
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.textview.MaterialTextView
import com.google.gson.Gson
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.data.vos.AllCinemaVO
import com.padc.csh.themovieapplication.dummy.safeTypeList
import kotlinx.android.synthetic.main.activity_cinema_detail.*
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.view_holder_facility.view.*


class CinemaDetailActivity : AppCompatActivity() {

    companion object{

        const val PARAM_CINEMA="CINEMA"
        fun newIntent(context:Context,cinemaVO:String):Intent{
            var intent=Intent(context,CinemaDetailActivity::class.java)
            intent.putExtra(PARAM_CINEMA,cinemaVO)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cinema_detail)
        var param=intent.getStringExtra(PARAM_CINEMA)
        var cinemaVO=Gson().fromJson<AllCinemaVO>(param,AllCinemaVO::class.java)

        bindData(cinemaVO)
        setUpActionListener()
    }

    private fun bindData(cinemaVO: AllCinemaVO?) {
        tvCinemaName.text=cinemaVO?.name.toString()
        tvCinemaLocation.text=cinemaVO?.address.toString()
        setUpDefaultMovie(cinemaVO?.promoVdoUrl.toString())
        addChipGroup(cinemaVO?.safety ?: listOf())

        cinemaVO?.facilities?.forEach{
            var view=layoutInflater.inflate(R.layout.view_holder_facility,null,false)
            Glide.with(this).load(it.img).into(view.ivFacilityCinemaDetail)
            view.tvFacilityCinemaDetail.text=it.title
            flowLayout.addView(view,0)
        }

    }

    private fun addChipGroup(newSafetyList: List<String>) {
        newSafetyList.forEach {
            var chip = Chip(this)
            chip.text = it
            chip.setTextColor(resources.getColor(R.color.color111111, null))
            chip.width = 198
            chip.height = 21

            //chip.setPadding(10,0,10,0)
            // chip.backgroundDrawable=resources.getDrawable(R.drawable.bg_safety_type_cinema_detail,null)
            chip.setChipBackgroundColorResource(R.color.colorAccent)
            chip.chipCornerRadius = 8f
            //val drawable = ChipDrawable.createFromAttributes(this, null, 0, R.style.safeChipAppearance)
            //chip.setChipDrawable(drawable)
            chip.setTextAppearanceResource(R.style.safeChipTextAppearance)
            chipGroupSafeTypeCinemaDetail.addView(chip)
        }

    }

    private fun setUpActionListener() {
        btnPlayCinemaDetailScrn.setOnClickListener {

            //visibilty
            vvCinemaDetail.visibility = View.VISIBLE

            vvCinemaDetail.requestFocus()
            vvCinemaDetail.setOnPreparedListener(object : MediaPlayer.OnPreparedListener {
                override fun onPrepared(p0: MediaPlayer?) {
                    vvCinemaDetail.start()
                }

            })
            ///visibilty gone
            ivVideoCoverCinemaDetail.visibility = View.GONE
            btnPlayCinemaDetailScrn.visibility = View.GONE

        }
    }

    private fun setUpDefaultMovie(videoUrl:String) {

       // val videoUrl =
        //    "https://media.geeksforgeeks.org/wp-content/uploads/20201217192146/Screenrecorder-2020-12-17-19-17-36-828.mp4?_=1"

        val videoUri = Uri.parse(videoUrl)
        vvCinemaDetail.setMediaController(MediaController(this))
        vvCinemaDetail.setVideoURI(videoUri)

        //set up default cover photo
//        Glide.with(this)
//            .load(videoUri).centerCrop()
//            .placeholder(R.drawable.ic_forestgump).into(ivVideoCoverCinemaDetail)
    }
}