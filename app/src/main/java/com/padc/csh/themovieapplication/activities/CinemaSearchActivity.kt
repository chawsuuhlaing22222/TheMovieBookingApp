package com.padc.csh.themovieapplication.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.adapters.AllCinemaListAdapter
import com.padc.csh.themovieapplication.adapters.MovieCinemaAdapter
import com.padc.csh.themovieapplication.data.models.MovieBookingModel
import com.padc.csh.themovieapplication.data.models.MovieBookingModelImpl
import com.padc.csh.themovieapplication.data.vos.AllCinemaVO
import com.padc.csh.themovieapplication.data.vos.CinemaTimeSlotVO
import com.padc.csh.themovieapplication.data.vos.CinemaVO
import com.padc.csh.themovieapplication.delegates.MovieCinemaDelegate
import com.padc.csh.themovieapplication.delegates.MovieCinemaSeatConditionDelegate
import com.padc.csh.themovieapplication.dummy.movieFormats
import com.padc.csh.themovieapplication.dummy.movieGenreList
import com.padc.csh.themovieapplication.dummy.movieShowMonths
import com.padc.csh.themovieapplication.utils.ConfigUtils
import kotlinx.android.synthetic.main.activity_cinema_search.*
import kotlinx.android.synthetic.main.activity_movie_search.*
import kotlinx.android.synthetic.main.view_item_search_toolbar.view.*
import java.time.LocalDate

class CinemaSearchActivity : AppCompatActivity(), MovieCinemaDelegate,
    MovieCinemaSeatConditionDelegate {
    private var searchFlag = 0
    private var spinnerFacilitiesFlag = 0
    private var spinnerFormatFlag = 0
    lateinit var mMovieCinemaListAdapter: AllCinemaListAdapter
    private var cinemaList:List<AllCinemaVO> = listOf()

    //search
    private var searchResult:MutableList<AllCinemaVO> = mutableListOf()

    //model
    private var mTheMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    //lateinit var mCinemaAdapter: MovieCinemaAdapter
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cinema_search)

        toolBarCinemaSearch.tvSearchMovieName.setHint("Search the cinema")
        setUpRecycler()
        setUpSpinner()
        setUpActionListener()
        requestData()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun requestData() {
        var token = "Bearer ${ConfigUtils.getInstance().getToken()}"
        var selectedDate= LocalDate.now()
        mTheMovieBookingModel.getAllCinemaFromDB {

            cinemaList = it
            mMovieCinemaListAdapter.setNewData(it)
        }
    }


    private fun setUpSpinner() {

        var facilitiesAdapter = ArrayAdapter(
            this,
            R.layout.view_item_custom_spinner_dropdown,
            R.id.tvSpinnerSelectedValue,
            resources.getStringArray(R.array.genres)
        )
        spinnerFacilities.adapter = facilitiesAdapter


        var moveiFormatAdapter = ArrayAdapter(
            this,
            R.layout.view_item_custom_spinner_dropdown,
            R.id.tvSpinnerSelectedValue,
            resources.getStringArray(R.array.formats)
        )
        spinnerMovieFormatCinemaSearch.adapter = moveiFormatAdapter

    }

    private fun setUpActionListener() {
        toolBarCinemaSearch.tvSearchMovieName.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event.getAction() == KeyEvent.ACTION_DOWN) {

                    rvCinemaSearchResults.visibility = View.VISIBLE
                    performSearch(toolBarCinemaSearch.tvSearchMovieName.text?.trim().toString())

                return@OnEditorActionListener true
            }
            return@OnEditorActionListener false
        })

        //spinnermovie genre
        spinnerFacilities.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                p1: View?,
                position: Int,
                p3: Long
            ) {
                spinnerFacilitiesFlag = spinnerFacilitiesFlag + 1
                if (spinnerFacilitiesFlag == 1) {
                    tvSelectedFacilities.text = "Facilities"
                } else {
                    tvSelectedFacilities.text = movieGenreList.get(position)
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        //rlgenre make trigger spinnermoviegenre
        rlCinemaFacilities.setOnClickListener {
            spinnerFacilities.performClick()
        }

        //spinner format
        spinnerMovieFormatCinemaSearch.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    p1: View?,
                    position: Int,
                    p3: Long
                ) {
                    spinnerFormatFlag += 1
                    if (spinnerFormatFlag == 1) {
                        tvSelectedMovieFormatCinemaSearch.text = "Format"
                    } else {
                        tvSelectedMovieFormatCinemaSearch.text = movieFormats.get(position)
                    }

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }

        //rlformat make trigger spinnerMovieFormat
        rlMovieFormatCinemaSearch.setOnClickListener {
            spinnerMovieFormatCinemaSearch.performClick()
        }


    }

    private fun performSearch(cinema: String) {

        searchResult.clear()

        cinemaList.forEach {

            if(it.name?.contains(cinema,true) == true){
                searchResult.add(it)
            }
        }
      mMovieCinemaListAdapter.setNewData(searchResult)

    }

    private fun setUpRecycler() {
        //mCinemaAdapter = MovieCinemaAdapter(this, this)
        //rvCinemaSearchResults.adapter = mCinemaAdapter
        mMovieCinemaListAdapter= AllCinemaListAdapter(this)
        rvCinemaSearchResults.adapter=mMovieCinemaListAdapter
        rvCinemaSearchResults.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onMovieCinema(cinemaVO: CinemaVO) {
      //  mCinemaAdapter.setSelectedPosition(position)
    }

    override fun onMovieCinemaAtFrag(cinemaVO: AllCinemaVO) {
        var cinema= Gson().toJson(cinemaVO)
        startActivity( CinemaDetailActivity.newIntent(this,cinema) )
    }

    override fun onMovieCinemaSeatPlanClick(timeSlotVO: CinemaTimeSlotVO) {
        startActivity(Intent(this, CinemaDetailActivity::class.java))
    }

}