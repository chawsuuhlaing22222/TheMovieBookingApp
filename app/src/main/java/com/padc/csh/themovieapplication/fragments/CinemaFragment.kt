package com.padc.csh.themovieapplication.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.activities.CinemaDetailActivity
import com.padc.csh.themovieapplication.activities.CinemaSearchActivity
import com.padc.csh.themovieapplication.adapters.MovieCinemaAdapter
import com.padc.csh.themovieapplication.adapters.MovieCinemaSeatConditionAdapter
import com.padc.csh.themovieapplication.delegates.MovieCinemaDelegate
import com.padc.csh.themovieapplication.delegates.MovieCinemaSeatConditionDelegate
import kotlinx.android.synthetic.main.activity_movie_booking.*
import kotlinx.android.synthetic.main.fragment_cinema.*
import kotlinx.android.synthetic.main.view_item_toolbar_movie.view.*


class CinemaFragment : Fragment(),MovieCinemaDelegate,MovieCinemaSeatConditionDelegate {

    lateinit var mMovieCinemaListAdapter:MovieCinemaAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cinema, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
        setUpActionListener()
    }

    private fun setUpActionListener() {
        toolBarCinemaScrn.ivSearch.setOnClickListener {
            startActivity(Intent(requireContext(),CinemaSearchActivity::class.java))
        }
    }

    private fun setUpRecycler(){
        //movie cinema rv
        mMovieCinemaListAdapter=MovieCinemaAdapter(this,this)
        rvCinemaList.adapter=mMovieCinemaListAdapter
        rvCinemaList.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)

    }

    override fun onMovieCinema(position: Int) {
        mMovieCinemaListAdapter.setSelectedPosition(position)
    }

    override fun onMovieCinemaSeatPlanClick() {
        startActivity(Intent(requireContext(),CinemaDetailActivity::class.java))
    }


}