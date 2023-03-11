package com.padc.csh.themovieapplication.data.models

import com.padc.csh.themovieapp.data.vos.ActorVO
import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapplication.data.vos.*
import com.padc.csh.themovieapplication.network.response.CheckOTPResponse

interface MovieBookingModel {



    fun getMovieDetail(
        movieId:String,
        onSuccess:(MovieVO)-> Unit,
        onFailure:(String)->Unit
    )


    //start for movie booking app
    fun getOtp(
        phoneNo:String,
        onSuccess:(OtpVO)-> Unit,
        onFailure:(String)->Unit
    )

    fun checkOtp(
        phoneNo:String,
        otp:String,
        onSuccess:(CheckOTPResponse)-> Unit,
        onFailure:(String)->Unit
    )

    fun getCities(
        onSuccess:(List<CityVO>)-> Unit,
        onFailure:(String)->Unit
    )

    fun getCityFromDB(
        onSuccess:(List<CityVO>)-> Unit
    )

    fun getBanners(
        onSuccess:(List<BannerVO>)-> Unit,
        onFailure:(String)->Unit
    )

    fun getMovieList(
        status:String,
        onSuccess:(List<MovieVO>)-> Unit,
        onFailure:(String)->Unit
    )

    fun getCinemaList(
        token:String,
        date:String,
        onSuccess:(List<CinemaVO>)-> Unit,
        onFailure:(String)->Unit
    )

    fun getCinemaTimeSlotList(
        onSuccess:(CinemaTimeSlotColorVO)-> Unit,
        onFailure:(String)->Unit
    )

    fun getCinemaTimeSlotFromDB(
        onSuccess:(List<TimeSlotColorVO>)-> Unit
    )

    fun getCinemaSeatPlan(
        token:String?,
        timeSlotId:String?,
        date:String?,
        onSuccess:(List<List<SeatVO>>)-> Unit,
        onFailure:(String)->Unit
    )


}