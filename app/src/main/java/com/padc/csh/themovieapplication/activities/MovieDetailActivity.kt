package com.padc.csh.themovieapplication.activities

import android.content.Context
import android.content.Intent
import android.icu.text.DateFormat.*
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import android.widget.MediaController
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import com.padc.csh.themovieapp.data.vos.ActorVO
import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.data.models.MovieBookingModel
import com.padc.csh.themovieapplication.data.models.MovieBookingModelImpl
import com.padc.csh.themovieapplication.delegates.MovieCastAdapter
import com.padc.csh.themovieapplication.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.activity_movie_detail.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*

class MovieDetailActivity : AppCompatActivity() {

    lateinit var mMovieCastAdapter: MovieCastAdapter

    //lateinit var mMovieGenreAdapter: MovieGenreAdapter
    //model
    private val movieModel: MovieBookingModel = MovieBookingModelImpl

    companion object {
        const val FROM_ACTIVITY = "FROM_ACTIVITY"
        const val MOVIE_ID = "movie id"
        fun newIntent(context: Context?, flag: String, movieId: String): Intent {
            var intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(FROM_ACTIVITY, flag)
            intent.putExtra(MOVIE_ID, movieId)
            return intent
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        //GET PARAM
        val fromActivity = intent.getStringExtra(FROM_ACTIVITY)
        val movieId: String = intent.getStringExtra(MOVIE_ID) ?: ""
        when (fromActivity) {
            "now" -> {
                rlNotiCard.visibility = View.GONE
            }
            "comming" -> {
                rlNotiCard.visibility = View.VISIBLE
            }
            else -> {
                rlNotiCard.visibility = View.GONE
            }
        }

        setUpRecycler()
        setUpActionListener()
        setUpDefaultMovie()
        //setUpMovieGenreChip()
        movieId?.let {
            requestData(movieId)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun requestData(movieId: String) {
        movieModel.getMovieDetail(movieId, {
            bindData(it)

        }, {
            showErrorMsg(it)
        })


    }

    private fun bindActorData(it: List<ActorVO>) {
        mMovieCastAdapter.setNewData(it)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun bindData(it: MovieVO) {
        setUpMovieGenreChip(it.genres)

        Glide.with(this).load("${IMAGE_BASE_URL}${it.posterPath}").into(ivVideoCover)
        Glide.with(this).load("${IMAGE_BASE_URL}${it.posterPath}").into(vvMovieCover)

        tvMovieNameDetailScrn.text = it.originalTtitle
        tvPointMovieDetailScrn.text = it.rating.toString()
        // tvReleaseDate.text=it.releaseDate.toString()
        tvReleaseDate.text = changeDateFormat(it.releaseDate.toString(), it.runtime ?: 0)
        tvStoryLine.text = it.overview


        bindActorData(it.casts ?: listOf())

    }


    private fun setUpMovieGenreChip(genreList: List<String>?) {
        chipGroupMovieGenre.removeAllViews()
        genreList?.forEach {

            var chip = Chip(this)
            chip.text = it
            chip.setChipBackgroundColorResource(R.color.colorAccent)
            chip.setPadding(8, 5, 8, 5)
            chip.setTextAppearance(R.style.movieGenreChipTextAppearance)
            chipGroupMovieGenre.addView(chip)
        }
    }

    private fun setUpActionListener() {
        vvMoviePlayIcon.setOnClickListener {

            //visibilty
            vvMovie.visibility = View.VISIBLE

            vvMovie.requestFocus()
            vvMovie.setOnPreparedListener(object : MediaPlayer.OnPreparedListener {
                override fun onPrepared(p0: MediaPlayer?) {
                    vvMovie.start()
                }

            })
            ///visibilty gone
            vvMovieCover.visibility = View.GONE
            vvMoviePlayIcon.visibility = View.GONE

        }

        //back press
        ivBackArrowMvDetail.setOnClickListener {
            onBackPressed()
        }

        //item listener
        btnBooking.setOnClickListener {
            startActivity(Intent(this, CinemaTimeSlotActivity::class.java))
        }


    }

    override fun onResume() {
        super.onResume()

    }


    private fun setUpDefaultMovie() {

        val videoUrl =
            "https://media.geeksforgeeks.org/wp-content/uploads/20201217192146/Screenrecorder-2020-12-17-19-17-36-828.mp4?_=1"

        val videoUri = Uri.parse(videoUrl)
        vvMovie.setMediaController(MediaController(this))
        vvMovie.setVideoURI(videoUri)

        //set up default cover photo
        Glide.with(this)
            .load(videoUri).centerCrop()
            .placeholder(R.drawable.placeholder_cast_img).into(vvMovieCover)
    }


    private fun setUpRecycler() {
        mMovieCastAdapter = MovieCastAdapter()
        rvCastPersonList.adapter = mMovieCastAdapter
        rvCastPersonList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }

    fun showErrorMsg(error: String) {
        var view = movieDetail
        Snackbar.make(view, "$error", Snackbar.LENGTH_SHORT).show()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun changeDateFormat(date: String, runTime: Int): String {

        val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val date = formattedDate.parse(date)
        val myString = DateFormat.getMediumDateFormat(this).format(date)

        // diff date
        var period = Period.between(LocalDate.of(date.year, date.month, date.day), LocalDate.now())
        tvReleaseDateNoti.text =
            getString(R.string.releasing_in_5days, "${period.months}m,${period.days}")

        //duration
        // var simpleTime=SimpleDateFormat("HH, mm")
        // val time: String =simpleTime.format(Date(runTime * 1000L))
        var hours = runTime / 3600;
        var minutes = (runTime % 3600) / 60
        if (hours == 0) {
            tvDuration.text = "$minutes min"
        } else {
            tvDuration.text = "$hours hr: $minutes min"
        }


        return myString
    }

}