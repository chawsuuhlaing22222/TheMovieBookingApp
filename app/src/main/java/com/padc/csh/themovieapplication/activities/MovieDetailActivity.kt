package com.padc.csh.themovieapplication.activities

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
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


    companion object{
        const val FROM_ACTIVITY="FROM_ACTIVITY"
        fun newIntent(context: Context?,flag:String):Intent{
            var intent=Intent(context,MovieDetailActivity::class.java)
            intent.putExtra(FROM_ACTIVITY,flag)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        //GET PARAM
        val fromActivity=intent.getStringExtra(FROM_ACTIVITY)
        when(fromActivity){
            "now"->{
                rlNotiCard.visibility=View.GONE
            }
            "comming"->{
                rlNotiCard.visibility=View.VISIBLE
            }
            else->{
                rlNotiCard.visibility=View.GONE
            }
        }

         setUpRecycler()
         setUpActionListener()
         setUpDefaultMovie()
    }

    private fun setUpActionListener() {
        vvMoviePlayIcon.setOnClickListener {

            //visibilty
            vvMovie.visibility=View.VISIBLE

            vvMovie.requestFocus()
            vvMovie.setOnPreparedListener(object:MediaPlayer.OnPreparedListener{
                override fun onPrepared(p0: MediaPlayer?) {
                    vvMovie.start()
                }

            })
            ///visibilty gone
            vvMovieCover.visibility=View.GONE
            vvMoviePlayIcon.visibility=View.GONE

        }

        //back press
        ivBackArrowMvDetail.setOnClickListener {
            onBackPressed()
        }

        //item listener
        btnBooking.setOnClickListener {
            startActivity(Intent(this,MovieBookingActivity::class.java))
        }


    }

    override fun onResume() {
        super.onResume()

    }


    private fun setUpDefaultMovie(){

        val videoUrl =
            "https://media.geeksforgeeks.org/wp-content/uploads/20201217192146/Screenrecorder-2020-12-17-19-17-36-828.mp4?_=1"

       //val videoUrl="https://www.youtube.com/watch?v=bC6RTYb3QLU"
        val videoUri=Uri.parse(videoUrl)
        vvMovie.setMediaController(MediaController(this))
        vvMovie.setVideoURI(videoUri)

        //set up default cover photo
       // vvMovieCover.setImageURI(videoUri)
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

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_toolbar_movie_detail,menu)
//        return true
//    }
}