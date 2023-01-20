package com.padc.csh.themovieapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.adapters.DateAdapter
import kotlinx.android.synthetic.main.activity_movie_booking.*

class MovieBookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_booking)

        rvDate.adapter=DateAdapter()
        rvDate.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    }
}