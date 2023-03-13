package com.padc.csh.themovieapplication.network

import com.padc.csh.themovieapplication.data.vos.OtpVO
import com.padc.csh.themovieapplication.data.vos.SeatListVO
import com.padc.csh.themovieapplication.network.response.*
import com.padc.csh.themovieapplication.utils.*
import retrofit2.Call
import retrofit2.http.*

interface TheMovieBookingApi {



    //start of movie booking api
    @FormUrlEncoded
    @POST("${API_GET_OTP}")
    fun getOtp(
        @Field("phone") phone:String
    ):Call<OtpVO>

    @FormUrlEncoded
    @POST("${API_CHECK_OTP}")
    fun checkOtp(
        @Field("phone") phone:String,
        @Field("otp") otp:String
    ):Call<CheckOTPResponse>

    @GET("${API_GET_CITIES}")
    fun getCities():Call<CityResponse>

    @GET("${API_GET_BANNERS}")
    fun getBanners():Call<BannerResponse>

    @GET("${API_GET_MOVIES}")
    fun getMovieList(
        @Query("status") status:String?
    ):Call<MovieListResponse>

    @GET("${API_GET_MOVIE_DETAIL}")
    fun getMovieDetail(
        @Path("movie_id") movieId:String?
    ):Call<MovieDetailResponse>


    @GET("${API_GET_CINEMALIST}")
    fun getCinemaList(
        @Header("Authorization") authorization:String?,
        @Query("date") date:String?
    ):Call<CinemaListResponse>

    @GET("${API_GET_CINEMA_TIMESLOT_COLORLIST}")
    fun getCinemaTimeSlotColorList():Call<CinemaTimeSlotColorResponse>

    @GET("$API_GET_SEAT_PLAN")
    fun getCinemaSeatPlan(
        @Header("Authorization") authorization: String?,
        @Query("cinema_day_timeslot_id") timeSlotId: String?,
        @Query("booking_date") bookingDate: String?
    ):Call<SeatListResponse>

    @GET("$API_GET_SNACK_CATEGORIES")
    fun getSnackCategoryList(
        @Header("Authorization") authorization: String?,
    ):Call<SnackCategoryResponse>

    @GET("$API_GET_SNACK_ALL")
    fun getSnackAll(
        @Header("Authorization") authorization: String?,
    ):Call<SnackResponse>

    @GET("$API_GET_SNACK_ALL")
    fun getSnackByCategoryId(
        @Header("Authorization") authorization: String?,
        @Query("category_id") categoryId:Int?
    ):Call<SnackResponse>








}