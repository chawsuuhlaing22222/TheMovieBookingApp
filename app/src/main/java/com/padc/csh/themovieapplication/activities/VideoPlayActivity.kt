package com.padc.csh.themovieapplication.activities

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.padc.csh.themovieapplication.R
import kotlinx.android.synthetic.main.activity_movie_detail.vvMovie
import kotlinx.android.synthetic.main.activity_video_play.*

class VideoPlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_play)

        btnStat.setOnClickListener {
            setUpDefaultMovie()
        }

    }

    private fun setUpDefaultMovie(){
        val videoUrl =
            "https://media.geeksforgeeks.org/wp-content/uploads/20201217192146/Screenrecorder-2020-12-17-19-17-36-828.mp4?_=1"

        vvMovie.setMediaController(MediaController(this))
        vvMovie.setVideoURI(Uri.parse(videoUrl))
        vvMovie.requestFocus()
        vvMovie.start()
    }
}