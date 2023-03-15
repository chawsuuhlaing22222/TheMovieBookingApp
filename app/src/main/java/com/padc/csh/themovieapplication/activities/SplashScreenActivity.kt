package com.padc.csh.themovieapplication.activities

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.data.models.MovieBookingModel
import com.padc.csh.themovieapplication.data.models.MovieBookingModelImpl
import com.padc.csh.themovieapplication.utils.ConfigUtils
import com.padc.csh.themovieapplication.utils.showErrorMsg
import kotlinx.android.synthetic.main.activity_splash_screen.*


class SplashScreenActivity : AppCompatActivity() {

    //model
    private var mTheMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    private var token:String=""
    lateinit var countDownTimer: CountDownTimer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        requestData()

    }

    private fun checkLoginUser() {
        if (token.isNotBlank()) {
            var city = ConfigUtils.getInstance().getCity()
            if (city.isNotBlank()) {
                startActivity(MainActivity.newIntent(context = this, city))
            } else {
                startActivity(Intent(this, ChooseLocationActivity::class.java))
            }
        } else {
            var intent = Intent(this@SplashScreenActivity, LoginChooseActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }


    private fun requestData() {
        mTheMovieBookingModel.getCities(
            {
                var view = rlSplash
                showErrorMsg("Successful", view)
            }, {
                var view = rlSplash
                showErrorMsg(it, view)
            }
        )

        mTheMovieBookingModel.getCinemaTimeSlotList({
            Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()

        }, {
            var view = rlSplash
            showErrorMsg(it, view)
        })

        mTheMovieBookingModel.getProfile {
            token= it.token.toString()
            ConfigUtils.getInstance().saveToken(token)
        }
    }


    private fun countDown() {
        countDownTimer = object : CountDownTimer(4000, 1000) {
            //40000 milli seconds is total time, 1000 milli seconds is time interval
            override fun onTick(millisUntilFinished: Long) {
                when (millisUntilFinished) {
                    in 3000L..4000L -> {
                        changeFirstCircleColor()
                    }
                    in 2000L..3000L -> {
                        changeSecondCircleColor()
                    }
                    in 1000L..2000L -> {
                        changeThirdCircleColor()
                    }
                    else -> {
                        changeFourthCircleColor()
                    }
                }
            }

            override fun onFinish() {

//                var intent = Intent(this@SplashScreenActivity, LoginChooseActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//                startActivity(intent)
                 checkLoginUser()

            }
        }.start()
    }


    private fun changeFirstCircleColor() {
        ivFirstCircle.setImageResource(R.drawable.ic_baseline_circle_active_24dp)
        ivFourthCircle.setImageResource(R.drawable.ic_baseline_circle_inactive_24dp)
    }

    private fun changeSecondCircleColor() {
        ivSecondCircle.setImageResource(R.drawable.ic_baseline_circle_active_24dp)
        ivFirstCircle.setImageResource(R.drawable.ic_baseline_circle_inactive_24dp)
    }

    private fun changeThirdCircleColor() {
        ivThirdCircle.setImageResource(R.drawable.ic_baseline_circle_active_24dp)
        ivSecondCircle.setImageResource(R.drawable.ic_baseline_circle_inactive_24dp)

    }

    private fun changeFourthCircleColor() {
        ivFourthCircle.setImageResource(R.drawable.ic_baseline_circle_active_24dp)
        ivThirdCircle.setImageResource(R.drawable.ic_baseline_circle_inactive_24dp)

    }

    override fun onResume() {

        countDown()
        super.onResume()
    }

    override fun onBackPressed() {
        countDownTimer.cancel()
        super.onBackPressed()
    }
}