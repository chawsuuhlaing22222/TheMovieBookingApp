package com.padc.csh.themovieapplication.data.models

import android.content.Context
import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapplication.data.vos.*
import com.padc.csh.themovieapplication.network.dataagents.MovieBookingDataAgent
import com.padc.csh.themovieapplication.network.dataagents.MovieBookingDataAgentImpl
import com.padc.csh.themovieapplication.network.response.CheckOTPResponse
import com.padc.csh.themovieapplication.persistence.TheMovieBookingDatabase

object MovieBookingModelImpl:MovieBookingModel {

    //dataagent
    private val mTheMovieBookingDataAgent:MovieBookingDataAgent = MovieBookingDataAgentImpl

    //database
    private var mTheMovieBookingDatabase:TheMovieBookingDatabase?=null
    fun initMovieBookingDB(context: Context){
        mTheMovieBookingDatabase=TheMovieBookingDatabase.getTheMovieBookingDbInstance(context)
    }

    override fun getProfile(onSuccess: (ProfileVO) -> Unit) {
        mTheMovieBookingDatabase?.movieBookingDao()?.getProfileVO()?.let { onSuccess(it) }
    }


    override fun getMovieDetail(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //from db
//        val movie=mTheMovieBookingDatabase?.movieBookingDao()?.getSingleMovie(movieId.toInt())
//        movie?.let {
//            onSuccess(it)
//        }

        mTheMovieBookingDataAgent.getMovieDetail(movieId,{

            onSuccess(it)
        },{
            onFailure(it)
        })

    }


    //start for movie booking app
    override fun getOtp(phoneNo: String, onSuccess: (OtpVO) -> Unit, onFailure: (String) -> Unit) {
        mTheMovieBookingDataAgent.getOtp(phoneNo,onSuccess,onFailure)
    }

    override fun checkOtp(
        phoneNo: String,
        otp: String,
        onSuccess: (CheckOTPResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingDataAgent.checkOtp(phoneNo, otp, {
            var profileVO=it.data
            profileVO?.token=it.token
            //insert profile to db
            profileVO?.let {
                mTheMovieBookingDatabase?.movieBookingDao()?.insetProfileWithToken(it)
            }

            onSuccess(it)

            }, onFailure)
        }

    override fun getCities(onSuccess: (List<CityVO>) -> Unit, onFailure: (String) -> Unit) {

        mTheMovieBookingDataAgent.getCities(
            {
               //insert citylist
                mTheMovieBookingDatabase?.movieBookingDao()?.insertCityList(it)
                onSuccess(it)
            },{
                onFailure(it)
            }
        )
    }


    override fun getCityFromDB(onSuccess: (List<CityVO>) -> Unit) {
        onSuccess(mTheMovieBookingDatabase?.movieBookingDao()?.getCityList() ?: listOf())
    }

    override fun getBanners(onSuccess: (List<BannerVO>) -> Unit, onFailure: (String) -> Unit) {
        mTheMovieBookingDataAgent.getBanners(onSuccess, onFailure)
    }

    override fun getMovieList(
        status: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingDataAgent.getMovieList(status, onSuccess, onFailure)
    }

    override fun getCinemaList(
        token: String,
        date: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingDataAgent.getCinemaList(token, date, onSuccess, onFailure)
    }

    override fun getCinemaTimeSlotList(
        onSuccess: (CinemaTimeSlotColorVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingDataAgent.getCinemaTimeSlotColorList({
            var v=it.value
     //       mTheMovieBookingDatabase?.movieBookingDao()?.insertTimeSlotColor(it.value as List<TimeSlotColorVO>)
            onSuccess(it)
        }
          , onFailure)
    }

    override fun getCinemaTimeSlotFromDB(
        onSuccess: (List<TimeSlotColorVO>) -> Unit,

    ) {
        onSuccess(mTheMovieBookingDatabase?.movieBookingDao()?.getTimeSlotColor() ?: listOf())
    }

    override fun getCinemaSeatPlan(
        token: String?,
        timeSlotId: String?,
        date: String?,
        onSuccess: (List<List<SeatVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingDataAgent.getCinemaSeatPlan(token,timeSlotId, date,
          onSuccess,onFailure )
    }

    override fun getSnackCategory(
        token: String?,
        onSuccess: (List<SnackCategoryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingDataAgent.getSnackCategory(token, onSuccess, onFailure)
    }

    override fun getSnackAll(
        token: String?,
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingDataAgent.getSnackAll(token, onSuccess, onFailure)
    }

    override fun getSnackByCategoryId(
        token: String?,
        categoryId: Int?,
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingDataAgent.getSnackByCategoryId(token,categoryId, onSuccess, onFailure)
    }

    override fun getPaymentTypes(
        token: String?,
        onSuccess: (List<PaymentTypeVO>?) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingDataAgent.getPaymentTypes(token, onSuccess, onFailure)
    }

    override fun postCheckOut(
        token: String?,
        requestBody: CheckOutRequestVO,
        onSuccess: (CheckOutResponseVO?) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingDataAgent.postCheckOut(token, requestBody, onSuccess, onFailure)
    }

    override fun insertTicket(ticketVO: TicketVO) {
        mTheMovieBookingDatabase?.movieBookingDao()?.insertTicket(ticketVO)
    }

    override fun deleteProfile() {
        mTheMovieBookingDatabase?.movieBookingDao()?.deleteProfile()
    }
}


