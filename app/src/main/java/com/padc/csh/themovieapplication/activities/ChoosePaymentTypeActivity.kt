package com.padc.csh.themovieapplication.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.adapters.PaymentTypeAdapter
import com.padc.csh.themovieapplication.data.models.MovieBookingModel
import com.padc.csh.themovieapplication.data.models.MovieBookingModelImpl
import com.padc.csh.themovieapplication.data.vos.*
import com.padc.csh.themovieapplication.delegates.PaymentDelegate
import com.padc.csh.themovieapplication.utils.ConfigUtils
import com.padc.csh.themovieapplication.utils.showErrorMsg
import kotlinx.android.synthetic.main.activity_check_out_acitivy.*
import kotlinx.android.synthetic.main.activity_choose_payment_type.*

class ChoosePaymentTypeActivity : AppCompatActivity(), PaymentDelegate {

    lateinit var mPaymentAdapter: PaymentTypeAdapter
    //model
    private var mTheMovieBookingModel:MovieBookingModel =MovieBookingModelImpl

    var jsonMovie:String=""
    var jsonCinema:String=""
    var jsonBookingDate:String=""
    var jsonTimeSlotId:String=""
    var jsonSeatList:String=""
    var jsonSnackList:String=""
    companion object{

        const val SEAT_LIST = "seat list"
        const val MOVIE="MOVIE"
        const val CINEMA="CINEMA"
        const val IEXTRA_TIMESLOT_ID = "ID"
        const val BOOKING_DATE = "BOOKING DATE"
        const val SNACK_LIST="snack list"
        fun newIntent(context: Context,  timeSlotIdd: String,
                               bookingDate: String, movie:String,
                               cinema:String, seatList: String, snackList:String): Intent
        {
            var intent = Intent(context, ChoosePaymentTypeActivity::class.java)
            intent.putExtra(IEXTRA_TIMESLOT_ID, timeSlotIdd)
            intent.putExtra(BOOKING_DATE, bookingDate)
            intent.putExtra(MOVIE,movie)
            intent.putExtra(CINEMA,cinema)
            intent.putExtra(SEAT_LIST,seatList)
            intent.putExtra(SNACK_LIST,snackList)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_payment_type)

        getParma()
        setUpPaymentRecyclerView()
        requestData()
    }

    private fun getParma() {
        jsonMovie= intent.getStringExtra(MOVIE).toString()
        jsonCinema= intent.getStringExtra(CINEMA).toString()
        jsonBookingDate= intent.getStringExtra(BOOKING_DATE).toString()

        jsonTimeSlotId= intent.getStringExtra(IEXTRA_TIMESLOT_ID).toString()

        jsonSeatList=intent.getStringExtra(SEAT_LIST).toString()

        jsonSnackList=intent.getStringExtra(SNACK_LIST).toString()

    }

    private fun requestData() {
        var token="Bearer "+ConfigUtils.getInstance().getToken()
        mTheMovieBookingModel?.getPaymentTypes(token,{
            mPaymentAdapter.setNewData(it)
        },{
           showErrorMsg(it,paymentView)
        })
    }

    private fun setUpPaymentRecyclerView() {
        mPaymentAdapter = PaymentTypeAdapter(this)
        rvPaymentType.adapter = mPaymentAdapter
        rvPaymentType.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    override fun onChoosePayment(paymentTypeVO: PaymentTypeVO) {
        var checkOutSnackList :MutableList<CheckOutSnackVO> = mutableListOf()
        var movie=Gson().fromJson(jsonMovie,MovieVO::class.java)
        var cinema=Gson().fromJson(jsonCinema,CinemaVO::class.java)

        var seatType=object :TypeToken<List<SeatVO>>(){}.type
        var snackType=object :TypeToken<List<SnackVO>>(){}.type

        var seatList=Gson().fromJson<List<SeatVO>>(jsonSeatList,seatType)
        var snackList=Gson().fromJson<List<SnackVO>>(jsonSnackList,snackType)

        cinema.timeslots?.forEach {
            if(it.id==jsonTimeSlotId?.toInt()){
                //tvMovieShowTimeCheckoutScrn.text=it.start_time
            }
        }

        var seatNames= mutableListOf<String>()

        snackList.forEach {
            //totalPriceForSnack += it.price.times(it.count)
            checkOutSnackList.add(CheckOutSnackVO(it.id,it.count))
        }
        seatList.forEach {
            seatNames.add(it.seatName ?: "")
            //totalPriceForSeat+=it.price
        }
        var seatName=seatNames.joinToString (",".toString())
        var checkOutRequest=CheckOutRequestVO(jsonTimeSlotId.toInt(),seatName,jsonBookingDate,movie.id?.toLong(),
        paymentTypeVO.id,checkOutSnackList.toList())

        var token="Bearer "+ConfigUtils.getInstance().getToken()
        mTheMovieBookingModel.postCheckOut(token,checkOutRequest,{

            var checkoutResponse=Gson().toJson(it)
             startActivity(TicketConfirmationActivity.newIntent(this,jsonMovie,jsonCinema,checkoutResponse))
        },{
            showErrorMsg(it,paymentView)
        })

    }
}