package com.padc.csh.themovieapplication

import android.app.Application
import com.padc.csh.themovieapplication.data.models.MovieBookingModelImpl
import com.padc.csh.themovieapplication.utils.ConfigUtils

class TheMovieBookingApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        MovieBookingModelImpl.initMovieBookingDB(applicationContext)

        ConfigUtils.initConfigUtils(applicationContext)
    }
}