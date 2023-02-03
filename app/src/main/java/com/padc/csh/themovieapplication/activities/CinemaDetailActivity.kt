package com.padc.csh.themovieapplication.activities

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import com.bumptech.glide.Glide
import com.padc.csh.themovieapplication.R
import kotlinx.android.synthetic.main.activity_cinema_detail.*
import kotlinx.android.synthetic.main.activity_movie_detail.*

class CinemaDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cinema_detail)

        setUpDefaultMovie()
        setUpActionListener()
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