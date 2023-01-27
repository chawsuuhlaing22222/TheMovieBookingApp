package com.padc.csh.themovieapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.slider.Slider
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
        setUpActionListener()
    }

    private fun setUpActionListener() {
        btnBuyTicket.setOnClickListener {
            startActivity(Intent(this,GetSnackActivity::class.java))
        }

        sbZoom.addOnChangeListener(object : Slider.OnChangeListener {

            override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {
                Toast.makeText(this@CinemaSeatPlanActivity, "progress is $value", Toast.LENGTH_SHORT).show()
            }

        })

        tvPluslbl.setOnClickListener {
            var progress=sbZoom.value
            if(progress<sbZoom.valueTo){
                sbZoom.value=progress+10
            }
        }

        tvMinuslbl.setOnClickListener {
            var progress=sbZoom.value
            if(progress>0){
                sbZoom.value=progress-10
            }
        }
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