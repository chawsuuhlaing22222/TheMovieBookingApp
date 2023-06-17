package com.padc.csh.themovieapplication.data.models

import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapplication.data.vos.*
import com.padc.csh.themovieapplication.network.response.CheckOTPResponse

interface MovieBookingModel {

    fun getProfile(
        onSuccess: (ProfileVO) -> Unit,

        )

    fun deleteProfile(

    )

    fun deleteTicket(

    )



    fun getMovieDetail(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun searchMoviesByTypeAndName(
        type:String,
        movieName:String,
        onSuccess: (List<MovieVO>) -> Unit,
    )


    //start for movie booking app
    fun getOtp(
        phoneNo: String,
        onSuccess: (OtpVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun checkOtp(
        phoneNo: String,
        otp: String,
        onSuccess: (CheckOTPResponse) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCities(
        onSuccess: (List<CityVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCityFromDB(
        onSuccess: (List<CityVO>) -> Unit
    )

    fun getBanners(
        onSuccess: (List<BannerVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMovieList(
        status: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCinemaList(
        token: String,
        date: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCinemaTimeSlotList(
        onSuccess: (CinemaTimeSlotColorVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCinemaTimeSlotFromDB(
        onSuccess: (List<TimeSlotColorVO>) -> Unit
    )

    fun getCinemaSeatPlan(
        token: String?,
        timeSlotId: String?,
        date: String?,
        onSuccess: (List<List<SeatVO>>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSnackCategory(
        token: String?,
        onSuccess: (List<SnackCategoryVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSnackAll(
        token: String?,
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSnackByCategoryId(
        token: String?,
        categoryId: Int?,
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPaymentTypes(
        token: String?,
        onSuccess: (List<PaymentTypeVO>?) -> Unit,
        onFailure: (String) -> Unit
    )

    fun postCheckOut(
        token: String?,
        requestBody: CheckOutRequestVO,
        onSuccess: (CheckOutResponseVO?) -> Unit,
        onFailure: (String) -> Unit
    )

    fun insertTicket(ticketVO: TicketVO)

    fun getAllTickets(onSuccess:(List<TicketVO>)-> Unit)

    fun getMovieVideo(
        movieId: Int,
        onSuccess:(List<MovieVideoVO>)-> Unit,
        onFailure:(String)->Unit
    )

    //for cinema fragment
    fun getAllCinemaFromNewtwork(
        onSuccess:(List<AllCinemaVO>)-> Unit,
        onFailure:(String)->Unit
    )

    fun getAllCinemaFromDB(
        onSuccess:(List<AllCinemaVO>)-> Unit,
    )

    fun getTimeSlotColor(status:Int,onSuccess:(TimeSlotColorVO)-> Unit)
}