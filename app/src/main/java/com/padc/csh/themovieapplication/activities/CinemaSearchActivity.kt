package com.padc.csh.themovieapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.adapters.MovieCinemaAdapter
import com.padc.csh.themovieapplication.delegates.MovieCinemaDelegate
import com.padc.csh.themovieapplication.delegates.MovieCinemaSeatConditionDelegate
import com.padc.csh.themovieapplication.dummy.movieFormats
import com.padc.csh.themovieapplication.dummy.movieGenreList
import com.padc.csh.themovieapplication.dummy.movieShowMonths
import kotlinx.android.synthetic.main.activity_cinema_search.*
import kotlinx.android.synthetic.main.activity_movie_search.*
import kotlinx.android.synthetic.main.view_item_search_toolbar.view.*

class CinemaSearchActivity : AppCompatActivity(),MovieCinemaDelegate,MovieCinemaSeatConditionDelegate {
    private var searchFlag=0
    private var spinnerFacilitiesFlag=0
    private var spinnerFormatFlag=0

    lateinit var mCinemaAdapter:MovieCinemaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cinema_search)

        toolBarCinemaSearch.tvSearchMovieName.setHint("Search the cinema")
        setUpRecycler()
        setUpSpinner()
        setUpActionListener()
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
                searchFlag = searchFlag + 1
                if (searchFlag == 1) {
                    rvCinemaSearchResults.visibility = View.VISIBLE
                }
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
                spinnerFacilitiesFlag=spinnerFacilitiesFlag+1
                if(spinnerFacilitiesFlag==1){
                    tvSelectedFacilities.text ="Facilities"
                }else{
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
        spinnerMovieFormatCinemaSearch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                p1: View?,
                position: Int,
                p3: Long
            ) {
                spinnerFormatFlag+=1
                if(spinnerFormatFlag==1){
                    tvSelectedMovieFormatCinemaSearch.text ="Format"
                }else{
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

    private fun setUpRecycler() {
        mCinemaAdapter= MovieCinemaAdapter(this,this)
        rvCinemaSearchResults.adapter=mCinemaAdapter
        rvCinemaSearchResults.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }

    override fun onMovieCinema(position: Int) {
        mCinemaAdapter.setSelectedPosition(position)
    }

    override fun onMovieCinemaSeatPlanClick() {
        startActivity(Intent(this,CinemaDetailActivity::class.java))
    }

}