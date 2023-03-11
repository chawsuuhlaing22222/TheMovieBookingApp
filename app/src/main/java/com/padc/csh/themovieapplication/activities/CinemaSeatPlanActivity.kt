package com.padc.csh.themovieapplication.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.slider.Slider
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.adapters.ChildSeatAdapter
import com.padc.csh.themovieapplication.adapters.SeatPlanAdapter
import com.padc.csh.themovieapplication.data.models.MovieBookingModel
import com.padc.csh.themovieapplication.data.models.MovieBookingModelImpl
import com.padc.csh.themovieapplication.data.vos.SeatListVO
import com.padc.csh.themovieapplication.data.vos.SeatVO
import com.padc.csh.themovieapplication.delegates.ChildSeatDelegate
import com.padc.csh.themovieapplication.delegates.SeatPlanDelegate
import com.padc.csh.themovieapplication.utils.ConfigUtils
import com.padc.csh.themovieapplication.utils.showErrorMsg
import kotlinx.android.synthetic.main.activity_cinema_seat_plan.*

class CinemaSeatPlanActivity : AppCompatActivity(), ChildSeatDelegate, SeatPlanDelegate {
    //model
    private var mTheMovieModel: MovieBookingModel = MovieBookingModelImpl

    //lateinit var mSeatPlanAdapter: SeatPlanAdapter
    lateinit var mSeatPlanAdapter: ChildSeatAdapter

    companion object {
        const val IEXTRA_TIMESLOT_ID = "ID"
        const val BOOKING_DATE = "BOOKING DATE"
        fun newIntent(context: Context, timeSlotIdd: String, bookingDate: String): Intent {
            var intent = Intent(context, CinemaSeatPlanActivity::class.java)
            intent.putExtra(IEXTRA_TIMESLOT_ID, timeSlotIdd)
            intent.putExtra(BOOKING_DATE, bookingDate)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cinema_seat_plan)

        //get param
        var token = ConfigUtils.getInstance().getToken()
        var timeSlotId = intent.getStringExtra(IEXTRA_TIMESLOT_ID) ?: ""
        var bookingDate = intent.getStringExtra(BOOKING_DATE) ?: ""

        setUpToolbar()
        setUpSeatPlanRecyclerView()
        setUpActionListener()
        requestData(token, timeSlotId, bookingDate)
    }

    private fun requestData(token: String, timeSlotIdd: String, bookingDate: String) {
        var newToken = "Bearer $token"
        mTheMovieModel.getCinemaSeatPlan(newToken, timeSlotIdd, bookingDate, {

            //var seatList = it
            var seatList=addSpaceSeat(it)
            mSeatPlanAdapter.setUpData(seatList)

        }, {
            var view = viewCinemaSeat
            showErrorMsg(it, view)
        })
    }

    private fun setUpActionListener() {

        btnBuyTicket.setOnClickListener {
            startActivity(Intent(this, GetSnackActivity::class.java))
        }

        sbZoom.addOnChangeListener(object : Slider.OnChangeListener {

            override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {
                Toast.makeText(
                    this@CinemaSeatPlanActivity,
                    "progress is $value",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })

        tvPluslbl.setOnClickListener {
            var progress = sbZoom.value
            if (progress < sbZoom.valueTo) {
                sbZoom.value = progress + 10
            }
        }

        tvMinuslbl.setOnClickListener {
            var progress = sbZoom.value
            if (progress > 0) {
                sbZoom.value = progress - 10
            }
        }
    }

    private fun setUpSeatPlanRecyclerView() {
        //  mSeatPlanAdapter = SeatPlanAdapter(this, this, this)
        mSeatPlanAdapter = ChildSeatAdapter(this)
        rvSeatPlanList.adapter = mSeatPlanAdapter
        rvSeatPlanList.layoutManager =
            GridLayoutManager(this, 18)
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolBarSeatPlanScrn)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_white_24dp)
        supportActionBar?.title = ""
    }

    override fun onClickChildSeat() {

    }

    private fun addSpaceSeat(seatListParam:List<List<SeatVO>>):List<List<SeatVO>>{
        var spaceVO=SeatVO(0,"space","","",0)
        var mutalbeSeatList=seatListParam as MutableList<MutableList<SeatVO>>
        mutalbeSeatList.forEach {
            it.add(5, spaceVO)
            it.add(6,spaceVO)
            it.add(11,spaceVO)
            it.add(12,spaceVO)
        }
        var  seatList=mutalbeSeatList as List<List<SeatVO>>
        return seatList
    }
}