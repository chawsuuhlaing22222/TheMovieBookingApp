package com.padc.csh.themovieapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.adapters.DateAdapter
import com.padc.csh.themovieapplication.adapters.MovieCinemaAdapter
import com.padc.csh.themovieapplication.delegates.DateDelegate
import com.padc.csh.themovieapplication.delegates.MovieCinemaDelegate
import com.padc.csh.themovieapplication.delegates.MovieCinemaSeatConditionDelegate
import kotlinx.android.synthetic.main.activity_movie_booking.*
import kotlinx.android.synthetic.main.view_holder_cinema_item.*

class MovieBookingActivity : AppCompatActivity(),MovieCinemaDelegate,MovieCinemaSeatConditionDelegate,DateDelegate {

    lateinit var mMovieCinemaAdapter: MovieCinemaAdapter
    lateinit var mDateAdapter: DateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_booking)

        setUpAdapter()
    }

    private fun setUpAdapter() {
        mDateAdapter=DateAdapter(this,this)
        rvDate.adapter = mDateAdapter
        rvDate.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        //movie cinema rv
        mMovieCinemaAdapter=MovieCinemaAdapter(this,this)
        rvMovieCinemaList.adapter=mMovieCinemaAdapter
        rvMovieCinemaList.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }

    override fun onMovieCinema(postition:Int) {
       mMovieCinemaAdapter.setSelectedPosition(postition)
    }


    override fun onDateCardClick(postition: Int) {
        mDateAdapter.setSelectedDatePosition(postition)
    }

    override fun onMovieCinemaSeatPlanClick() {
       startActivity(Intent(this,GetSnackActivity::class.java))
    }
}