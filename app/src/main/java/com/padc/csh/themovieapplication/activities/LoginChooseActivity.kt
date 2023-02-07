package com.padc.csh.themovieapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import com.padc.csh.themovieapplication.R
import kotlinx.android.synthetic.main.activity_login_choose.*
import kotlinx.android.synthetic.main.activity_movie_detail.*

class LoginChooseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_choose)

        setUpListener()

    }

//    override fun onBackPressed() {
//        finishAffinity()
//    }

    private fun setUpListener(){

        //spinner of country code
        spinnerCountryCode.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                ( parent?.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.colorPrimaryLight,theme))

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        //button of verify ph no
        btnVerifyPhNo.setOnClickListener {
            if(edtvMobileNum.text?.isNotBlank() == true){
                startActivity(Intent(this,GetOtpActivity::class.java))
            }else{
                edtvMobileNum.setError("Please enter phone number")
            }
        }

        //button of google signin
        btnGoogleSignIn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}