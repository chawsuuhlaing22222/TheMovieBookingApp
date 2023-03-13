package com.padc.csh.themovieapplication.network.dataagents

import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapplication.data.vos.*
import com.padc.csh.themovieapplication.network.response.CheckOTPResponse
import com.padc.csh.themovieapplication.network.response.MovieDetailResponse

interface MovieBookingDataAgent {
    fun getMovieDetail(
        movieId:String,
        onSuccess:(MovieVO)-> Unit,
        onFailure:(String)->Unit
    )


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

    fun getBanners(
        onSuccess:(List<BannerVO>)-> Unit,
        onFailure:(String)->Unit
    )

    fun getMovieList(
        status:String?,
        onSuccess:(List<MovieVO>)-> Unit,
        onFailure:(String)->Unit
    )

    fun getCinemaList(
        token:String?,
        date:String?,
        onSuccess:(List<CinemaVO>)-> Unit,
        onFailure: (String) -> Unit
    )

    fun getCinemaTimeSlotColorList(
        onSuccess:(CinemaTimeSlotColorVO)-> Unit,
        onFailure:(String)->Unit
    )

    fun getCinemaSeatPlan(
        token:String?,
        timeSlotId:String?,
        date:String?,
        onSuccess:(List<List<SeatVO>>)-> Unit,
        onFailure:(String)->Unit
    )

    fun getSnackCategory(
        token:String?,
        onSuccess:(List<SnackCategoryVO>)-> Unit,
        onFailure:(String)->Unit
    )

    fun getSnackAll(
        token:String?,
        onSuccess:(List<SnackVO>)-> Unit,
        onFailure:(String)->Unit
    )

    fun getSnackByCategoryId(
        token:String?,
        categoryId:Int?,
        onSuccess:(List<SnackVO>)-> Unit,
        onFailure:(String)->Unit
    )
}