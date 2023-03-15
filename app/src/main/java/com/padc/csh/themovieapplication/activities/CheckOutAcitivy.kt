package com.padc.csh.themovieapplication.activities

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.adapters.OrderedFoodDetailCheckOutAdapter
import com.padc.csh.themovieapplication.data.vos.CinemaVO
import com.padc.csh.themovieapplication.data.vos.SeatVO
import com.padc.csh.themovieapplication.data.vos.SnackVO
import com.padc.csh.themovieapplication.delegates.OrderFoolListChekoutDelegate
import com.padc.csh.themovieapplication.dummy.orderedFoodList
import com.padc.csh.themovieapplication.utils.changeStringToMedimnDateFormat
import kotlinx.android.synthetic.main.activity_check_out_acitivy.*
import kotlinx.android.synthetic.main.custom_view_ticket_cancel_policy.view.*
import java.text.SimpleDateFormat
import java.util.*

class CheckOutAcitivy : AppCompatActivity(), OrderFoolListChekoutDelegate {
    lateinit var mOrderFoodAdapter: OrderedFoodDetailCheckOutAdapter
    private var openOrderFoodDetailFlag = true
    private var orderedSnackList:MutableList<SnackVO> = mutableListOf()
    var totalPrice=0
    var totalPriceForSeat=0
    var totalPriceForSnack=0
    var jsonMovie:String=""
    var jsonCinema:String=""
    var jsonBookingDate:String=""
    var jsonTimeSlotId:String=""
    var jsonSeatList:String=""
    var jsonSnackList:String=""
    companion object{
        const val IEXTRA_DATA="from which activity"
        const val SEAT_LIST = "seat list"
        const val MOVIE="MOVIE"
        const val CINEMA="CINEMA"
        const val IEXTRA_TIMESLOT_ID = "ID"
        const val BOOKING_DATE = "BOOKING DATE"
        const val SNACK_LIST="snack list"
        fun newIntent(context: Context,fromAction:String):Intent{
            val intent=Intent(context,CheckOutAcitivy::class.java)
            intent.putExtra(IEXTRA_DATA,fromAction)
            return intent
        }


        fun newIntentFromSnack(context: Context,fromAction:String, timeSlotIdd: String,
                               bookingDate: String,movie:String,
                               cinema:String,seatList: String,snackList:String): Intent
        {
            var intent = Intent(context, CheckOutAcitivy::class.java)
            intent.putExtra(IEXTRA_DATA,fromAction)
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
        setContentView(R.layout.activity_check_out_acitivy)

        setUpViewVisibility()
        setUpRecyclerFoodList()
        bindData()
        setUpActionListener()

    }

    private fun setUpViewVisibility() {
        var fromAction=intent.getStringExtra(IEXTRA_DATA).toString()
        when(fromAction){
            "ticket"->{
                tvCheckOut.text="Ticekt Details"
                btnContinueCheckoutScrn.visibility=View.GONE
                rlTicketCancel.visibility=View.VISIBLE
                llTicketCancelPolicy.setBackgroundResource(R.drawable.bg_ticket_cancelation_policy_from_ticket)
            }
            else->{

            }
        }
    }

    private fun setUpActionListener() {

        ivDropDownArrowCheckoutScrn.setOnClickListener {
            if (!openOrderFoodDetailFlag) {
                openOrderFoodDetailFlag = true
                rvOrderedFoodListCheckoutScrn.visibility = View.VISIBLE
            } else {
                openOrderFoodDetailFlag = false
                rvOrderedFoodListCheckoutScrn.visibility = View.GONE
            }
        }

        llTicketCancelPolicy.setOnClickListener {
            createPolicyAlertDialog()
        }

        btnContinueCheckoutScrn.setOnClickListener {
            startActivity(ChoosePaymentTypeActivity.newIntent(this,jsonTimeSlotId,jsonBookingDate,jsonMovie,
            jsonCinema,jsonSeatList,jsonSnackList))
        }

    }

    private fun createPolicyAlertDialog() {
        var dialog = AlertDialog.Builder(this).create()
        var view =
            LayoutInflater.from(this).inflate(R.layout.custom_view_ticket_cancel_policy, null)
        dialog.setView(view)
        view.btnCloseTicketCancelationDialog.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    private fun setUpRecyclerFoodList() {
        mOrderFoodAdapter = OrderedFoodDetailCheckOutAdapter(this)
        rvOrderedFoodListCheckoutScrn.adapter = mOrderFoodAdapter
        rvOrderedFoodListCheckoutScrn.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }


    private fun bindData() {
        jsonMovie= intent.getStringExtra(MOVIE).toString()
        var movie=Gson().fromJson(jsonMovie,MovieVO::class.java)

        jsonCinema= intent.getStringExtra(CINEMA).toString()
        var cinema=Gson().fromJson(jsonCinema,CinemaVO::class.java)


        var bookingDate=intent.getStringExtra(BOOKING_DATE)
        if (bookingDate != null) {
            jsonBookingDate=bookingDate
        }

        var timeSlotIdd=intent.getStringExtra(IEXTRA_TIMESLOT_ID)
        if (timeSlotIdd != null) {
            jsonTimeSlotId=timeSlotIdd
        }

        var seatType=object :TypeToken<List<SeatVO>>(){}.type
        var snackType=object :TypeToken<List<SnackVO>>(){}.type

        jsonSeatList=intent.getStringExtra(SEAT_LIST).toString()
        var seatList=Gson().fromJson<List<SeatVO>>(jsonSeatList,seatType)

        jsonSnackList=intent.getStringExtra(SNACK_LIST).toString()
        var snackList=Gson().fromJson<List<SnackVO>>(jsonSnackList,snackType)


        tvMovieDateCheckoutScrn.text= bookingDate?.let { changeStringToMedimnDateFormat(this, it) }

        orderedSnackList=snackList.toMutableList()
        mOrderFoodAdapter.setNewData(snackList)

        cinema.timeslots?.forEach {
            if(it.id==timeSlotIdd?.toInt()){
                tvMovieShowTimeCheckoutScrn.text=it.start_time
            }
        }

        var seatNames= mutableListOf<String>()

        snackList.forEach {
            totalPriceForSnack += it.price.times(it.count)
        }
        seatList.forEach {
            seatNames.add(it.seatName ?: "")
            totalPriceForSeat+=it.price
        }
        totalPrice = totalPriceForSeat+totalPriceForSnack+500
        tvTotalAmtCheckoutScrn.text=getString(R.string.order_snack_price,totalPrice.toString())
        tvTotalOrderedFoodPriceCheckoutScrn.text=getString(R.string.order_snack_price,totalPriceForSnack.toString())
        tvTicketTypeCheckoutScrn.text=seatNames.joinToString ("," ).toString()
        tvTicketPriceCheckoutScrn.text=getString(R.string.order_snack_price,totalPriceForSeat.toString())


        tvMovieNameCheckoutScrn.text =
            Html.fromHtml("<font color='#ffffff'><b>${movie.originalTtitle}</b> </font><font color='#888888'>(3D)(U/A)</font>")
        tvCinemaNameCheckoutScrn.setText(Html.fromHtml("<font color='#00ff6a'>${cinema.name} </font><font color='#aaaaaa'>(SCREEN2)</font>"))
        tvTicketCountCheckoutScrn.text =
            Html.fromHtml("<font color='#aaaaaa'>M-Ticket (</font><font color='#00ff6a'>${seatList.size}</font><font color='#888888'>)</font>")
    }

    override fun onFoodCancel(snackVO: SnackVO) {
        var reducedAmt=snackVO.price.times(snackVO.count)
        totalPrice -=reducedAmt
        totalPriceForSnack -=reducedAmt
        tvTotalAmtCheckoutScrn.text=getString(R.string.order_snack_price,totalPrice.toString())
        tvTotalOrderedFoodPriceCheckoutScrn.text=getString(R.string.order_snack_price,totalPriceForSnack.toString())

        orderedSnackList.remove(snackVO)      //Toast.makeText(this,"cancel",Toast.LENGTH_SHORT).show()
        mOrderFoodAdapter.setNewData(orderedSnackList)


    }
}