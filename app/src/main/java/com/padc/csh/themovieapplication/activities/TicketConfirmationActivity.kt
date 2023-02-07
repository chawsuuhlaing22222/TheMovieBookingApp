package com.padc.csh.themovieapplication.activities

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Html
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog

import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.dummy.QRGContents
import com.padc.csh.themovieapplication.dummy.QRGEncoder
import kotlinx.android.synthetic.main.activity_check_out_acitivy.*
import kotlinx.android.synthetic.main.activity_ticket_confirmation.*
import kotlinx.android.synthetic.main.custom_view_ticket_cancel_policy.view.*
import kotlinx.android.synthetic.main.view_holder_ticket_item.view.*
import kotlinx.android.synthetic.main.view_item_toolbar_movie.*

class TicketConfirmationActivity : AppCompatActivity() {

    lateinit var dialog:Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_confirmation)

        generateQRCode()
        bindData()
        createPolicyAlertDialog()
        showCustomdialog()
        setUpActionListener()
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
        layoutMovieTicket.tvMovieNameTicketScrn.text= Html.fromHtml("<font color='#ffffff'><b>Black White</b> </font><font color='#888888'>(3D)(U/A)</font>")
        layoutMovieTicket.tvTicketTypeTicketScrn.setText(Html.fromHtml("<font color='#ffffff'><b>Gold-G8,G7</b></font><font color='#888888'>(SCREEN2)</font>"))
        layoutMovieTicket.tvTicketCountTicketScrn.text= Html.fromHtml("<font color='#888888'>M-Ticket (</font><font color='#00ff6a'>2</font><font color='#888888'>)</font>")
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