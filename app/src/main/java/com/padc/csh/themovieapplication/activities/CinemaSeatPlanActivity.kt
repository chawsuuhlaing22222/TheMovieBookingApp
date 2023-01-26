package com.padc.csh.themovieapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.adapters.SeatPlanAdapter
import com.padc.csh.themovieapplication.delegates.ChildSeatDelegate
import com.padc.csh.themovieapplication.delegates.SeatPlanDelegate
import kotlinx.android.synthetic.main.activity_cinema_seat_plan.*

class CinemaSeatPlanActivity : AppCompatActivity(),ChildSeatDelegate,SeatPlanDelegate {

    lateinit var mSeatPlanAdapter: SeatPlanAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cinema_seat_plan)

        setUpToolbar()
        setUpSeatPlanRecyclerView()
    }

    private fun setUpSeatPlanRecyclerView() {
        mSeatPlanAdapter= SeatPlanAdapter(this,this,this)
        rvSeatPlanList.adapter=mSeatPlanAdapter
        rvSeatPlanList.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolBarSeatPlanScrn)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_white_24dp)
    }

    override fun onClickChildSeat() {

    }
}