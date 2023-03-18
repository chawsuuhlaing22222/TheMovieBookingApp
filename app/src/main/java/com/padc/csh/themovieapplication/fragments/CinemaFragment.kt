package com.padc.csh.themovieapplication.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.activities.CinemaDetailActivity
import com.padc.csh.themovieapplication.activities.CinemaSearchActivity
import com.padc.csh.themovieapplication.adapters.AllCinemaListAdapter
import com.padc.csh.themovieapplication.adapters.MovieCinemaAdapter
import com.padc.csh.themovieapplication.adapters.MovieCinemaSeatConditionAdapter
import com.padc.csh.themovieapplication.data.models.MovieBookingModel
import com.padc.csh.themovieapplication.data.models.MovieBookingModelImpl
import com.padc.csh.themovieapplication.data.vos.AllCinemaVO
import com.padc.csh.themovieapplication.data.vos.CinemaTimeSlotVO
import com.padc.csh.themovieapplication.data.vos.CinemaVO
import com.padc.csh.themovieapplication.delegates.MovieCinemaDelegate
import com.padc.csh.themovieapplication.delegates.MovieCinemaSeatConditionDelegate
import com.padc.csh.themovieapplication.utils.ConfigUtils
import kotlinx.android.synthetic.main.activity_movie_booking.*
import kotlinx.android.synthetic.main.fragment_cinema.*
import kotlinx.android.synthetic.main.view_item_toolbar_movie.view.*
import java.time.LocalDate


class CinemaFragment : Fragment(),MovieCinemaDelegate,MovieCinemaSeatConditionDelegate {

    //model
    private var mTheMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    lateinit var mMovieCinemaListAdapter:AllCinemaListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cinema, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
        setUpActionListener()
        requestData()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun requestData() {
        var token = "Bearer ${ConfigUtils.getInstance().getToken()}"
        var selectedDate= LocalDate.now()
        mTheMovieBookingModel.getAllCinemaFromDB {
            mMovieCinemaListAdapter.setNewData(it)
        }
    }

    private fun setUpActionListener() {
        toolBarCinemaScrn.ivSearch.setOnClickListener {
            startActivity(Intent(requireContext(),CinemaSearchActivity::class.java))
        }
    }

    private fun setUpRecycler(){
        //movie cinema rv
        mMovieCinemaListAdapter= AllCinemaListAdapter(this)
        rvCinemaList.adapter=mMovieCinemaListAdapter
        rvCinemaList.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

    }

    override fun onMovieCinema(cinemaVO: CinemaVO) {
       // mMovieCinemaListAdapter.setSelectedPosition(position)
    }

    override fun onMovieCinemaAtFrag(cinemaVO: AllCinemaVO) {
        var cinema=Gson().toJson(cinemaVO)
        startActivity(context?.let { CinemaDetailActivity.newIntent(it,cinema) })
    }

    override fun onMovieCinemaSeatPlanClick(timeSlotVO: CinemaTimeSlotVO) {
        startActivity(Intent(requireContext(),CinemaDetailActivity::class.java))
    }


}