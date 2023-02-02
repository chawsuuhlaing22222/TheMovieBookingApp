package com.padc.csh.themovieapplication.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.adapters.CommingSoonMovieAdapter
import com.padc.csh.themovieapplication.adapters.NowShowingMovieAdapter
import com.padc.csh.themovieapplication.delegates.MovieListDelegate
import com.padc.csh.themovieapplication.dummy.movieFormats
import com.padc.csh.themovieapplication.dummy.movieGenreList
import com.padc.csh.themovieapplication.dummy.movieShowMonths
import kotlinx.android.synthetic.main.activity_movie_search.*
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.view_item_search_toolbar.view.*


class MovieSearchActivity : AppCompatActivity(), MovieListDelegate {

    private var searchFlag=0
    private var fromAction="now"
    private var spinnerGenreFlag=0
    private var spinnerFormatFlag=0
    private var spinnerMonthFlag=0

    lateinit var mNowShowingMovieAdapter: NowShowingMovieAdapter
    lateinit var mCommingSoonMovieAdapter: CommingSoonMovieAdapter

    companion object{
        var IEXTRA_FROMACTION="FROM ACTIVITY"
        fun newIntent(context: Context,fromAction:String):Intent{
            val intent=Intent(context,MovieSearchActivity::class.java)
            intent.putExtra(IEXTRA_FROMACTION,fromAction)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_search)
        //get extra data
         fromAction = intent.getStringExtra(IEXTRA_FROMACTION).toString()

        setUpRecycler()
        setUpActionListener()
        setUpSpinner()
    }

    private fun setUpSpinner() {

        var moveiGenreAdapter = ArrayAdapter(
            this,
            R.layout.view_item_custom_spinner_dropdown,
            R.id.tvSpinnerSelectedValue,
            resources.getStringArray(R.array.genres)
        )
        spinnerMovieGenre.adapter = moveiGenreAdapter


        var moveiFormatAdapter = ArrayAdapter(
            this,
            R.layout.view_item_custom_spinner_dropdown,
            R.id.tvSpinnerSelectedValue,
            resources.getStringArray(R.array.formats)
        )
        spinnerMovieFormat.adapter = moveiFormatAdapter

        var moveiShowMonthAdapter = ArrayAdapter(
            this,
            R.layout.view_item_custom_spinner_dropdown,
            R.id.tvSpinnerSelectedValue,
            resources.getStringArray(R.array.showMonths)
        )
        spinnerMovieShowMonth.adapter = moveiShowMonthAdapter
    }


    private fun setUpActionListener() {
        toolBarMovieSearch.tvSearchMovieName.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event.getAction() == KeyEvent.ACTION_DOWN) {
                searchFlag = searchFlag + 1
                if (searchFlag == 1) {
                    setUpViewVisibility()
                }
                return@OnEditorActionListener true
            }
            return@OnEditorActionListener false
        })

        //spinnermovie genre
        spinnerMovieGenre.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                p1: View?,
                position: Int,
                p3: Long
            ) {
                spinnerGenreFlag=spinnerGenreFlag+1
                if(spinnerGenreFlag==1){
                    tvSelectedMovieGenre.text ="Genres"
                }else{
                    tvSelectedMovieGenre.text = movieGenreList.get(position)
                }

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        //rlgenre make trigger spinnermoviegenre
        rlMovieGenre.setOnClickListener {
            spinnerMovieGenre.performClick()
        }

        //spinner format
        spinnerMovieFormat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                p1: View?,
                position: Int,
                p3: Long
            ) {
                spinnerFormatFlag+=1
                if(spinnerFormatFlag==1){
                    tvSelectedMovieFormat.text ="Format"
                }else{
                    tvSelectedMovieFormat.text = movieFormats.get(position)
                }

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        //rlformat make trigger spinnerMovieFormat
        rlMovieFormat.setOnClickListener {
            spinnerMovieFormat.performClick()
        }

        //spinner format
        spinnerMovieShowMonth.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                p1: View?,
                position: Int,
                p3: Long
            ) {
                spinnerMonthFlag+=1
                if(spinnerMonthFlag==1){
                    tvSelectedShowMonth.text ="Month"
                }else{
                    tvSelectedShowMonth.text = movieShowMonths.get(position)
                }

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        //rlformat make trigger spinnerMovieFormat
        rlMovieMonth.setOnClickListener {
            spinnerMovieShowMonth.performClick()
        }

    }

    private fun setUpViewVisibility() {

        llChooseMovieType.visibility = View.VISIBLE
        flSearchResultMovies.visibility = View.VISIBLE

        when (fromAction) {
            "now" -> {
                rlMovieMonth.visibility = View.GONE
                rvNowShowingMoviesSearchResult.visibility=View.VISIBLE
                rvCommingSoonMoviesSearchResult.visibility=View.GONE
            }
            "comming" -> {
                rlMovieMonth.visibility = View.VISIBLE
                rvNowShowingMoviesSearchResult.visibility=View.GONE
                rvCommingSoonMoviesSearchResult.visibility=View.VISIBLE
            }
            else -> {
                rlMovieMonth.visibility = View.GONE
            }
        }

    }
    private fun setUpRecycler() {
        mNowShowingMovieAdapter= NowShowingMovieAdapter(this)
        mCommingSoonMovieAdapter= CommingSoonMovieAdapter(this)

        rvNowShowingMoviesSearchResult.adapter=mNowShowingMovieAdapter
        rvNowShowingMoviesSearchResult.layoutManager= GridLayoutManager(this,2)

        rvCommingSoonMoviesSearchResult.adapter=mCommingSoonMovieAdapter
        rvCommingSoonMoviesSearchResult.layoutManager= GridLayoutManager(this,2)

    }
    override fun onTapNowShowingMovie() {
        startActivity(MovieDetailActivity.newIntent(this,"now"))
    }

    override fun onTapCommingSoonMovie() {
        startActivity(MovieDetailActivity.newIntent(this,"comming"))
    }
}