package com.padc.csh.themovieapplication.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.activities.MovieDetailActivity
import com.padc.csh.themovieapplication.delegates.MovieListDelegate
import com.padc.csh.themovieapplication.viewpods.MovieListViewPod
import kotlinx.android.synthetic.main.fragment_comming_soon_movie_list.*
import kotlinx.android.synthetic.main.fragment_now_showing_movie_list.*


class CommingSoonMovieListFragment : Fragment(),MovieListDelegate {

    lateinit var mMovieListViewPod: MovieListViewPod
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comming_soon_movie_list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpViewPod()
    }

    private fun setUpViewPod() {
        mMovieListViewPod= viewPodCommingSoonMovieList as MovieListViewPod
        mMovieListViewPod.setUpViewPod(this,"comming")
    }



    override fun onTapNowShowingMovie(movieId:String) {

    }

    override fun onTapCommingSoonMovie(movieId: String) {

    }

}