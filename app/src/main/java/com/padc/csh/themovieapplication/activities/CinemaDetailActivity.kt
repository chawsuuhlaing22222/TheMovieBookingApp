package com.padc.csh.themovieapplication.activities

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings.ZoomDensity
import android.widget.MediaController
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.dummy.safeTypeList
import kotlinx.android.synthetic.main.activity_cinema_detail.*
import kotlinx.android.synthetic.main.activity_movie_detail.*


class CinemaDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cinema_detail)

        setUpDefaultMovie()
        setUpActionListener()
        addChipGroup()
    }

    private fun addChipGroup() {
        safeTypeList.forEach{
            var chip=Chip(this)
            chip.text=it
            chip.setTextColor(resources.getColor(R.color.color111111,null))
            chip.setPadding(10,5,10,5)
            chip.setChipBackgroundColorResource(R.color.colorAccent)
            chip.chipCornerRadius = 4f
            //val drawable = ChipDrawable.createFromAttributes(this, null, 0, R.style.safeChipAppearance)
            //chip.setChipDrawable(drawable)
            chip.setTextAppearanceResource(R.style.safeChipTextAppearance)
            chipGroupSafeTypeCinemaDetail.addView(chip)
        }
    }

    private fun setUpActionListener(){
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
    private fun setUpDefaultMovie() {

        val videoUrl =
            "https://media.geeksforgeeks.org/wp-content/uploads/20201217192146/Screenrecorder-2020-12-17-19-17-36-828.mp4?_=1"

        val videoUri = Uri.parse(videoUrl)
        vvCinemaDetail.setMediaController(MediaController(this))
        vvCinemaDetail.setVideoURI(videoUri)

        //set up default cover photo
        Glide.with(this)
            .load(videoUri).centerCrop()
            .placeholder(R.drawable.ic_forestgump).into(ivVideoCoverCinemaDetail)
    }
}