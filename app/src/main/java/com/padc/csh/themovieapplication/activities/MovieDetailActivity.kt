package com.padc.csh.themovieapplication.activities

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.MediaController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.adapters.MovieGenreAdapter
import com.padc.csh.themovieapplication.delegates.MovieCastAdapter
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.activity_movie_detail.vvMovie
import kotlinx.android.synthetic.main.activity_video_play.*

class MovieDetailActivity : AppCompatActivity() {

    lateinit var mMovieCastAdapter: MovieCastAdapter
    lateinit var mMovieGenreAdapter: MovieGenreAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

         setUpToolbar()
        setUpRecycler()


    }

    override fun onResume() {
        super.onResume()
        setUpDefaultMovie()
    }


    private fun setUpDefaultMovie(){
        val videoUrl =
            "https://media.geeksforgeeks.org/wp-content/uploads/20201217192146/Screenrecorder-2020-12-17-19-17-36-828.mp4?_=1"

        vvMovie.setMediaController(MediaController(this))
        vvMovie.setVideoURI(Uri.parse(videoUrl))
        vvMovie.requestFocus()

        vvMovie.setOnPreparedListener(object:MediaPlayer.OnPreparedListener{
            override fun onPrepared(p0: MediaPlayer?) {
                vvMovie.start()
            }

        })

    }

    private fun setUpToolbar() {
        setSupportActionBar(toolBarMovieDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_white_24dp)

    }

    private fun setUpRecycler() {
        mMovieCastAdapter = MovieCastAdapter()
        rvCastPersonList.adapter = mMovieCastAdapter
        rvCastPersonList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        mMovieGenreAdapter= MovieGenreAdapter()
        rvMovieGenreDetailScrn.adapter=mMovieGenreAdapter
        rvMovieGenreDetailScrn.layoutManager=GridLayoutManager(this,3)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_movie_detail,menu)
        return true
    }
}