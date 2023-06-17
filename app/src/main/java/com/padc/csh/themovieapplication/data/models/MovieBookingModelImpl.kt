package com.padc.csh.themovieapplication.data.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapp.data.vos.NOW_PLAYING
import com.padc.csh.themovieapplication.data.vos.*
import com.padc.csh.themovieapplication.network.dataagents.MovieBookingDataAgent
import com.padc.csh.themovieapplication.network.dataagents.MovieBookingDataAgentImpl
import com.padc.csh.themovieapplication.network.dataagents.MovieDBDataAgent
import com.padc.csh.themovieapplication.network.dataagents.MovieDBDataAgentImpl
import com.padc.csh.themovieapplication.network.response.CheckOTPResponse
import com.padc.csh.themovieapplication.persistence.TheMovieBookingDatabase
import java.util.LinkedList

object MovieBookingModelImpl:MovieBookingModel {

    //dataagent
    private val mTheMovieBookingDataAgent:MovieBookingDataAgent = MovieBookingDataAgentImpl

    //dataagent
    private val mTheMovieDBDataAgent :MovieDBDataAgent = MovieDBDataAgentImpl

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
        val movie=mTheMovieBookingDatabase?.movieBookingDao()?.getSingleMovie(movieId.toInt())
        movie?.let {
            onSuccess(it)
        }

        mTheMovieBookingDataAgent.getMovieDetail(movieId,{
            it.type=movie?.type
            mTheMovieBookingDatabase?.movieBookingDao()?.insertSingleMovie(it)
            onSuccess(it)
        },{
            onFailure(it)
        })

    }

    override fun searchMoviesByTypeAndName(
        type: String,
        movieName: String,
        onSuccess: (List<MovieVO>) -> Unit,

    ) {
        onSuccess(mTheMovieBookingDatabase?.movieBookingDao()?.searchMovieListByTypeAndName(type,movieName) ?: listOf())
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

        onSuccess( mTheMovieBookingDatabase?.movieBookingDao()?.getMovieListByType(status) ?: listOf())

        mTheMovieBookingDataAgent.getMovieList(status,{

            it.forEach { movie -> movie.type = status }
            mTheMovieBookingDatabase?.movieBookingDao()?.insertMovieList(it)
            onSuccess(it)
        } , onFailure)
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
            var cinemaAnyColorList=it.value as ArrayList<*>
            //var cinemaColorList=cinemaAnyColorList.get(2) as ArrayList<*>
            var newConfigTimeSlotColor =  mutableListOf<TimeSlotColorVO>()

            for(anyConfig in cinemaAnyColorList) {
                val gson = Gson()
                val linkedTreeMap: LinkedTreeMap<*, *> = anyConfig as LinkedTreeMap<*, *>
                val timeslotColorVO: TimeSlotColorVO = gson.fromJson(gson.toJsonTree(linkedTreeMap), TimeSlotColorVO::class.java)
                newConfigTimeSlotColor.add(timeslotColorVO)
            }

           mTheMovieBookingDatabase?.movieBookingDao()?.insertTimeSlotColor( newConfigTimeSlotColor)
         //   mTheMovieBookingDatabase?.movieBookingDao()?.insertCinemaTimeSlotColorList(it.value)
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

    override fun getAllTickets(onSuccess: (List<TicketVO>) -> Unit) {

        onSuccess(mTheMovieBookingDatabase?.movieBookingDao()?.getAllTicket() ?: listOf())
    }

    override fun getMovieVideo(
        movieId: Int,
        onSuccess: (List<MovieVideoVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieDBDataAgent.getMovieVideo(movieId,onSuccess, onFailure)
    }

    override fun getAllCinemaFromNewtwork(
        onSuccess: (List<AllCinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingDataAgent.getAllCinema(
            {
                if (it != null) {
                    mTheMovieBookingDatabase?.movieBookingDao()?.insertAllCinemaList(it)
                }
                onSuccess(it)
            }
            , onFailure)
    }

    override fun getAllCinemaFromDB(onSuccess: (List<AllCinemaVO>) -> Unit) {
           onSuccess(mTheMovieBookingDatabase?.movieBookingDao()?.getAllCinemaList() ?: listOf())
    }

    override fun getTimeSlotColor(status: Int, onSuccess: (TimeSlotColorVO) -> Unit) {

        mTheMovieBookingDatabase?.movieBookingDao()?.getTimeSlotColorForStatus(status)
            ?.let { onSuccess(it) }
    }

    override fun deleteProfile() {
        mTheMovieBookingDatabase?.movieBookingDao()?.deleteProfile()
    }

    override fun deleteTicket() {
        mTheMovieBookingDatabase?.movieBookingDao()?.deleteTickets()
    }
}


