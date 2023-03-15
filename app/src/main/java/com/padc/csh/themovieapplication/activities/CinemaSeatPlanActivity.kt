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
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
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

    private var selectedSeatList:MutableList<SeatVO> = mutableListOf()

    companion object {
        const val MOVIE="MOVIE"
        const val CINEMA="CINEMA"
        const val IEXTRA_TIMESLOT_ID = "ID"
        const val BOOKING_DATE = "BOOKING DATE"

        fun newIntent(context: Context, timeSlotIdd: String, bookingDate: String,movie:String,cinema:String): Intent {
            var intent = Intent(context, CinemaSeatPlanActivity::class.java)
            intent.putExtra(IEXTRA_TIMESLOT_ID, timeSlotIdd)
            intent.putExtra(BOOKING_DATE, bookingDate)
            intent.putExtra(MOVIE,movie)
            intent.putExtra(CINEMA,cinema)
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
        var movieVo=intent.getStringExtra(MOVIE) ?: ""
        var selectedcinema=intent.getStringExtra(CINEMA) ?: ""
        var selectedDate=intent.getStringExtra(BOOKING_DATE) ?: ""
        var timeSlotId=intent.getStringExtra(IEXTRA_TIMESLOT_ID) ?: ""

        btnBuyTicket.setOnClickListener {
            var type=object :TypeToken<List<SeatVO>>(){}.type
            var seatList=Gson().toJson(selectedSeatList,type)
            startActivity(GetSnackActivity.newIntent(this,timeSlotId,selectedDate,movieVo,selectedcinema,seatList))
        }

        sbZoom.addOnChangeListener(object : Slider.OnChangeListener {

            override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {
                zoom.zoomTo(value,true)
//                Toast.makeText(
//                    this@CinemaSeatPlanActivity,
//                    "progress is $value",
//                    Toast.LENGTH_SHORT
//                ).show()
            }

        })

        tvPluslbl.setOnClickListener {
            var progress = sbZoom.value
            if (progress < sbZoom.valueTo) {
                sbZoom.value = (progress + 0.1).toFloat()
            }
        }

        tvMinuslbl.setOnClickListener {
            var progress = sbZoom.value
            if (progress > 0) {
                sbZoom.value = (progress - 0.1).toFloat()
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


    private fun addSpaceSeat(seatListParam:List<List<SeatVO>>):List<List<SeatVO>>{
        var spaceVO=SeatVO(0,"space","","",0,isSelected = false)
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

    override fun onSelectdSeat(seatVO: SeatVO) {
        selectedSeatList.add(seatVO)
        tvTicketCountSeatPlanScrn.text=getString(R.string.ticketCountLabel,selectedSeatList.count().toString())
        tvTicketPrice.text=selectedSeatList.count().times(seatVO.price).toString()
    }

    override fun onUnSelectdSeat(seatVO: SeatVO) {
       selectedSeatList.remove(seatVO)
        tvTicketCountSeatPlanScrn.text=getString(R.string.ticketCountLabel,selectedSeatList.count().toString())
        tvTicketPrice.text=selectedSeatList.count().times(seatVO.price).toString()
    }
}