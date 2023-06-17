package com.padc.csh.themovieapplication.activities

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Html
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.padc.csh.themovieapp.data.vos.MovieVO

import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.data.models.MovieBookingModel
import com.padc.csh.themovieapplication.data.models.MovieBookingModelImpl
import com.padc.csh.themovieapplication.data.vos.*
import com.padc.csh.themovieapplication.dummy.QRGContents
import com.padc.csh.themovieapplication.dummy.QRGEncoder
import com.padc.csh.themovieapplication.utils.BASE_URL
import com.padc.csh.themovieapplication.utils.IMAGE_BASE_URL
import com.padc.csh.themovieapplication.utils.changeStringToMedimnDateFormat
import kotlinx.android.synthetic.main.activity_check_out_acitivy.*
import kotlinx.android.synthetic.main.activity_ticket_confirmation.*
import kotlinx.android.synthetic.main.custom_view_ticket_cancel_policy.view.*
import kotlinx.android.synthetic.main.view_holder_ticket_item.*
import kotlinx.android.synthetic.main.view_holder_ticket_item.view.*
import kotlinx.android.synthetic.main.view_item_toolbar_movie.*
import okhttp3.internal.wait

class TicketConfirmationActivity : AppCompatActivity() {

    lateinit var dialog:Dialog
    lateinit var movieVO: MovieVO
    lateinit var cinemaVO: CinemaVO
    lateinit var checkOutResponseVO: CheckOutResponseVO

    //model
    private var mTheBookingModel:MovieBookingModel=MovieBookingModelImpl
    companion object{

        const val MOVIE="MOVIE"
        const val CINEMA="CINEMA"
        const val CHECKOUT_RESPONSE="checkout response"
        fun newIntent(context: Context,
                     movie:String,
                      cinema:String, checkOutResponse:String): Intent
        {
            var intent = Intent(context, TicketConfirmationActivity::class.java)
            intent.putExtra(MOVIE,movie)
            intent.putExtra(CINEMA,cinema)
            intent.putExtra(CHECKOUT_RESPONSE,checkOutResponse)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_confirmation)

        getParma()
        //generateQRCode()
        createPolicyAlertDialog()
        showCustomdialog()
        setUpActionListener()
        bindData()
    }

    private fun getParma() {
        movieVO=Gson().fromJson(intent.getStringExtra(MOVIE),MovieVO::class.java)
        cinemaVO=Gson().fromJson(intent.getStringExtra(CINEMA),CinemaVO::class.java)
        checkOutResponseVO=Gson().fromJson(intent.getStringExtra(CHECKOUT_RESPONSE),CheckOutResponseVO::class.java)

    }

    private fun showCustomdialog() {
        var countDownTimer=object : CountDownTimer(3000, 1000) {
            override fun onTick(p0: Long) {
                dialog.show()
            }

            override fun onFinish() {
             dialog.dismiss()
            }

        }.start()
    }

    private fun generateQRCode() {
        var en= QRGEncoder("mgmg",null,QRGContents.Type.TEXT,300)
        ivQRCodeConfirmationScrn.setImageBitmap(en.bitmap)
    }

    private fun bindData() {
      var movieId=""
        var movieName=""
        var cinemaId=""
        var cinemaName=""

        if(checkOutResponseVO.movieId==movieVO.id){
            layoutMovieTicket.tvMovieNameTicketScrn.text= Html.fromHtml("<font color='#ffffff'><b>${movieVO.originalTtitle}</b> </font><font color='#888888'>(3D)(U/A)</font>")
            movieId=movieVO.id.toString()
            movieName=movieVO.originalTtitle.toString()

        }
        if(cinemaVO.id==checkOutResponseVO.cinemaId){
            layoutMovieTicket.tvCinemaNameTicketScrn.text=cinemaVO.name
            cinemaId=cinemaVO.id.toString()
            cinemaName=cinemaVO.name.toString()

        }

        layoutMovieTicket.tvMovieShowTimeTicketScrn.text=checkOutResponseVO.timeslot?.start_time.toString()
        layoutMovieTicket.tvMovieDateTicketScrn.text= changeStringToMedimnDateFormat(this,checkOutResponseVO.bookingDate.toString())
        layoutMovieTicket.tvTicketTypeTicketScrn.setText(Html.fromHtml("<font color='#ffffff'><b>${checkOutResponseVO.seat}</b></font><font color='#888888'>(SCREEN2)</font>"))
        layoutMovieTicket.tvTicketCountTicketScrn.text= Html.fromHtml("<font color='#888888'>M-Ticket (</font><font color='#00ff6a'>${checkOutResponseVO.totalSeat}</font><font color='#888888'>)</font>")
        Glide.with(this).load("$BASE_URL/${checkOutResponseVO.qrCode}").into(ivQRCodeConfirmationScrn)
        Glide.with(this).load("$IMAGE_BASE_URL/${movieVO.posterPath}").into(ivMovieImgTicketScrn)

        var ticketVO= TicketVO(id = null, movieId = movieId.toInt(), movieName = movieName, cinemaId = cinemaId.toInt(), cinemaName = cinemaName,
        startTime = checkOutResponseVO.timeslot?.start_time.toString(), bookingDate =checkOutResponseVO.bookingDate.toString(), seatNameList =checkOutResponseVO.seat,
        seatCount = checkOutResponseVO.totalSeat.toString(), qrCode =checkOutResponseVO.qrCode,movieVO.posterPath,System.currentTimeMillis().toString())

        mTheBookingModel.insertTicket(ticketVO)
    }

    private fun createPolicyAlertDialog(){
        dialog= Dialog(this, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen)
        var view= LayoutInflater.from(this).inflate(R.layout.custom_dialog_overlay_confirmation_complete,null)
        dialog.setContentView(view)
        dialog.getWindow()?.setBackgroundDrawableResource(R.drawable.bg_custom_alert_overlay)
        dialog.setCanceledOnTouchOutside(false)

    }

    private fun setUpActionListener(){
        btnDone.setOnClickListener {
            var intent=Intent(this,MainActivity::class.java)
            intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
}