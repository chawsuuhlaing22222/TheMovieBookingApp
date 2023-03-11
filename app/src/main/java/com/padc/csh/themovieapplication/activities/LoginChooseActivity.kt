package com.padc.csh.themovieapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.data.models.MovieBookingModel
import com.padc.csh.themovieapplication.data.models.MovieBookingModelImpl
import com.padc.csh.themovieapplication.utils.showErrorMsg
import kotlinx.android.synthetic.main.activity_get_otp.*
import kotlinx.android.synthetic.main.activity_login_choose.*
import kotlinx.android.synthetic.main.activity_movie_detail.*

class LoginChooseActivity : AppCompatActivity() {

    //model
    private var mTheMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_choose)

        setUpListener()

    }

//    override fun onBackPressed() {
//        finishAffinity()
//    }


    private fun setUpListener() {

        //spinner of country code
        spinnerCountryCode.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                (parent?.getChildAt(0) as TextView).setTextColor(
                    resources.getColor(
                        R.color.colorPrimaryLight,
                        theme
                    )
                )

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        //button of verify ph no
        btnVerifyPhNo.setOnClickListener {
            var phoneNo = edtvMobileNum.text.toString()
            if (phoneNo.isNotBlank() == true) {

                if (checkPhoneNoIsValid(phoneNo)) {

                    phoneNo = phoneNo.replaceFirst("09", "959")
                    startActivity(GetOtpActivity.newIntent(this, phoneNo))
                } else {
                    edtvMobileNum.setError("Invalid  Phone No")
                    Toast.makeText(this, "Invalid  Phone No", Toast.LENGTH_SHORT).show()
                }

            } else {
                edtvMobileNum.setError("Please enter phone number")
            }
        }

        //button of google signin
        btnGoogleSignIn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    fun checkPhoneNoIsValid(phone: String): Boolean {
        var reg = "^09\\d{9}".toRegex()
        return reg.matches(phone)
    }
}