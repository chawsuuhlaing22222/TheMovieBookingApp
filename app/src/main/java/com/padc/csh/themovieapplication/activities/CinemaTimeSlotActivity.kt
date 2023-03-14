package com.padc.csh.themovieapplication.activities

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.adapters.DateAdapter
import com.padc.csh.themovieapplication.adapters.MovieCinemaAdapter
import com.padc.csh.themovieapplication.data.models.MovieBookingModel
import com.padc.csh.themovieapplication.data.models.MovieBookingModelImpl
import com.padc.csh.themovieapplication.data.vos.CinemaTimeSlotVO
import com.padc.csh.themovieapplication.data.vos.CinemaVO
import com.padc.csh.themovieapplication.data.vos.TimeSlotColorVO
import com.padc.csh.themovieapplication.delegates.DateDelegate
import com.padc.csh.themovieapplication.delegates.MovieCinemaDelegate
import com.padc.csh.themovieapplication.delegates.MovieCinemaSeatConditionDelegate
import com.padc.csh.themovieapplication.utils.ConfigUtils
import kotlinx.android.synthetic.main.activity_movie_booking.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class CinemaTimeSlotActivity : AppCompatActivity(), MovieCinemaDelegate,
    MovieCinemaSeatConditionDelegate, DateDelegate {

    lateinit var mMovieCinemaAdapter: MovieCinemaAdapter
    lateinit var mDateAdapter: DateAdapter

    //model
    private var mTheMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    private var selectedDate = "2023-03-11"
    private var selectedCinemaVO:CinemaVO ?=null

    companion object {

        var timeSlotList: List<TimeSlotColorVO> = listOf()
        const val MOVIE="MOVIE"
        fun newIntent(context: Context,movieVO: String):Intent{
            var intent=Intent(context,CinemaTimeSlotActivity::class.java)
            intent.putExtra(MOVIE,movieVO)
            return intent
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_booking)

        setUpAdapter()
        requestData(0)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun requestData(postition: Int) {

        var token = "Bearer ${ConfigUtils.getInstance().getToken()}"
        selectedDate = getDate(postition)

        mTheMovieBookingModel.getCinemaList(token, selectedDate, {
            selectedCinemaVO=it.get(0)
            mMovieCinemaAdapter.setNewData(it)
        }, {

        })

        mTheMovieBookingModel.getCinemaTimeSlotFromDB {
            timeSlotList = it
        }
    }

    private fun setUpAdapter() {
        mDateAdapter = DateAdapter(this, this)
        rvDate.adapter = mDateAdapter
        rvDate.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        //movie cinema rv
        mMovieCinemaAdapter = MovieCinemaAdapter(this, this)
        rvMovieCinemaList.adapter = mMovieCinemaAdapter
        rvMovieCinemaList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onMovieCinema(cinemaVO: CinemaVO) {
      selectedCinemaVO=cinemaVO
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDateCardClick(postition: Int) {
        mDateAdapter.setSelectedDatePosition(postition)
        requestData(postition)
    }

    override fun onMovieCinemaSeatPlanClick(timeSlotVO: CinemaTimeSlotVO) {
          var movieVo=intent.getStringExtra(MOVIE) ?: ""
          var selectedcinema=Gson().toJson(selectedCinemaVO)
        startActivity(
            CinemaSeatPlanActivity.newIntent(
                this,
                timeSlotVO.id.toString(),
                selectedDate,movieVo,selectedcinema
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDate(postition: Int): String {

        val localDate = LocalDate.now().plusDays(postition.toLong())
        return localDate.toString()
    }

}