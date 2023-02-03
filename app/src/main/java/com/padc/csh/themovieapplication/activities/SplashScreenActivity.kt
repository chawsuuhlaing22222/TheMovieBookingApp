package com.padc.csh.themovieapplication.activities

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.padc.csh.themovieapplication.R
import kotlinx.android.synthetic.main.activity_splash_screen.*


class SplashScreenActivity : AppCompatActivity() {

    lateinit var countDownTimer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //countDown()

    }


    private fun countDown(){
        countDownTimer=object :CountDownTimer(4000, 1000) {
            //40000 milli seconds is total time, 1000 milli seconds is time interval
            override fun onTick(millisUntilFinished: Long) {
                when(millisUntilFinished){
                    in 3000L..4000L->{
                        changeFirstCircleColor()
                    }
                    in 2000L..3000L->{
                        changeSecondCircleColor()
                    }
                    in 1000L..2000L->{
                        changeThirdCircleColor()
                    }
                    else->{
                        changeFourthCircleColor()
                    }
                }
            }
            override fun onFinish() {
                startActivity(Intent(applicationContext,LoginChooseActivity::class.java))
            }
        }.start()
    }


    private fun changeFirstCircleColor(){
        ivFirstCircle.setImageResource(R.drawable.ic_baseline_circle_active_24dp)
        ivFourthCircle.setImageResource(R.drawable.ic_baseline_circle_inactive_24dp)
    }

    private fun changeSecondCircleColor(){
        ivSecondCircle.setImageResource(R.drawable.ic_baseline_circle_active_24dp)
        ivFirstCircle.setImageResource(R.drawable.ic_baseline_circle_inactive_24dp)
    }

    private fun changeThirdCircleColor(){
        ivThirdCircle.setImageResource(R.drawable.ic_baseline_circle_active_24dp)
        ivSecondCircle.setImageResource(R.drawable.ic_baseline_circle_inactive_24dp)

    }
    private fun changeFourthCircleColor(){
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