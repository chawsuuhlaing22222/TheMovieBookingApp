package com.padc.csh.themovieapplication.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.data.models.MovieBookingModel
import com.padc.csh.themovieapplication.data.models.MovieBookingModelImpl
import com.padc.csh.themovieapplication.utils.ConfigUtils
import com.padc.csh.themovieapplication.utils.hideLoading
import com.padc.csh.themovieapplication.utils.showErrorMsg
import com.padc.csh.themovieapplication.utils.showLoading
import kotlinx.android.synthetic.main.activity_get_otp.*

class GetOtpActivity : AppCompatActivity() {

    //model
    private var mTheMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    private var phoneNo: String = ""
    private var otp: String = ""

    companion object {
        const val PHONE_NUMBER = "phone number"
        fun newIntent(context: Context, phoneNo: String): Intent {
            var intent = Intent(context, GetOtpActivity::class.java)
                .putExtra(PHONE_NUMBER, phoneNo)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_otp)

        phoneNo = intent.getStringExtra(PHONE_NUMBER).toString()

        setUpListener()
        phoneNo?.let {
            requestData(it)
        }

    }

    private fun requestData(phoneNo: String) {
        mTheMovieBookingModel.getOtp(phoneNo, {
            showErrorMsg(it.message ?: "", viewOtpScreen)
        }, {
            showErrorMsg(it, viewOtpScreen)
        })
    }

    private fun setUpListener() {

        //resend
        tvResendCode.setOnClickListener {
            requestData(phoneNo = phoneNo)
        }

        //click at confirm
        btnConfirmOtp.setOnClickListener {
            showLoading(this)
            otp = pinViewOtp.text.toString()
            checkOtp(phoneNo, otp)
        }


        //back btn
        ivBackGetOtpScrn.setOnClickListener {
            onBackPressed()
        }
    }

    private fun checkOtp(phoneNo: String, otp: String) {
        mTheMovieBookingModel.checkOtp(phoneNo, otp,
            {
                if (it.isSuccessful()) {
                    hideLoading()
                    ConfigUtils.getInstance().saveToken(it.token.toString())
                    startActivity(Intent(this, ChooseLocationActivity::class.java))
                } else {
                    hideLoading()
                }
            }, {
                hideLoading()
                showErrorMsg(it, viewOtpScreen)
            })
    }

}