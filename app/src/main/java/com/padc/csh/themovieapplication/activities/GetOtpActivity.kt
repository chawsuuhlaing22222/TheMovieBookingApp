package com.padc.csh.themovieapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.padc.csh.themovieapplication.R
import kotlinx.android.synthetic.main.activity_get_otp.*

class GetOtpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_otp)

        setUpListener()
    }

    private fun setUpListener(){

        //pinview

        //click at confirm
        btnConfirmOtp.setOnClickListener {
          startActivity(Intent(this,ChooseLocationActivity::class.java))
        }


        //back btn
        ivBackGetOtpScrn.setOnClickListener {
            onBackPressed()
        }
    }

}