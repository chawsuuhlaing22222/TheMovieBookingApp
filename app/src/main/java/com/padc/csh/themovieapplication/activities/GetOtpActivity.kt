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
        //click at confirm
        btnConfirmOtp.setOnClickListener {
            var flag=false

            if(edtFirstOtpCode.text.isNullOrBlank()){
                edtFirstOtpCode.setError(" ")
                flag=true
            }

            if(edtSecondOtpCode.text.isNullOrBlank()){
                edtSecondOtpCode.setError(" ")
                flag=true
            }

            if(edtThirdOtpCode.text.isNullOrBlank()){
                edtThirdOtpCode.setError(" ")
                flag=true
            }

            if(edtFourthOtpCode.text.isNullOrBlank()){
                edtFourthOtpCode.setError(" ")
                flag=true
            }

            if(edtFifthOtpCode.text.isNullOrBlank()){
                edtFifthOtpCode.setError(" ")
                flag=true
            }

            if(edtSixOtpCode.text.isNullOrBlank()){
                edtSixOtpCode.setError(" ")
                flag=true
            }

            if(!flag){
                startActivity(Intent(this,ChooseLocationActivity::class.java))
            }

        }

        //back btn
        ivBackGetOtpScrn.setOnClickListener {
            onBackPressed()
        }
    }

}