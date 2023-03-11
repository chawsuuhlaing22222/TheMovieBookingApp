package com.padc.csh.themovieapplication.network.dataagents

import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapplication.data.vos.*
import com.padc.csh.themovieapplication.network.TheMovieBookingApi
import com.padc.csh.themovieapplication.network.response.*
import com.padc.csh.themovieapplication.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MovieBookingDataAgentImpl:MovieBookingDataAgent {

    var mTheMovieBookingApi:TheMovieBookingApi?=null
    init {
        val okHttpClient=OkHttpClient.Builder()
            .connectTimeout(15,TimeUnit.SECONDS)
            .readTimeout(15,TimeUnit.SECONDS)
            .writeTimeout(15,TimeUnit.SECONDS)
            .build()

        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mTheMovieBookingApi=retrofit.create(TheMovieBookingApi::class.java)
    }

   override fun getMovieDetail(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {

        mTheMovieBookingApi?.getMovieDetail(movieId)?.enqueue(
            object :Callback<MovieDetailResponse>{
                override fun onResponse(call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>) {

                    if(response.isSuccessful){
                        response.body()?.data?.let {
                            onSuccess(it)
                        }
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                   onFailure(t.message ?: "")
                }

            }
        )
    }


    override fun getOtp(phoneNo: String, onSuccess: (OtpVO) -> Unit, onFailure: (String) -> Unit) {
        mTheMovieBookingApi?.getOtp(phoneNo)?.enqueue(
            object :Callback<OtpVO>{
                override fun onResponse(call: Call<OtpVO>, response: Response<OtpVO>) {
                    if(response.isSuccessful){
                        response?.body()?.let {
                            onSuccess(it)
                        }
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<OtpVO>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            }
        )
    }

    override fun checkOtp(
        phoneNo: String,
        otp: String,
        onSuccess: (CheckOTPResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.checkOtp(phoneNo,otp)?.enqueue(
            object :Callback<CheckOTPResponse>{
                override fun onResponse(
                    call: Call<CheckOTPResponse>,
                    response: Response<CheckOTPResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let{
                            onSuccess(it)
                        }
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<CheckOTPResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            }
        )
    }

    override fun getCities(onSuccess: (List<CityVO>) -> Unit, onFailure: (String) -> Unit) {
        mTheMovieBookingApi?.getCities()?.enqueue(
            object :Callback<CityResponse>{
                override fun onResponse(
                    call: Call<CityResponse>,
                    response: Response<CityResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            onSuccess(it.data ?: listOf())
                        }
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<CityResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            }
        )
    }

    override fun getBanners(onSuccess: (List<BannerVO>) -> Unit, onFailure: (String) -> Unit) {

        mTheMovieBookingApi?.getBanners()?.enqueue(
            object :Callback<BannerResponse>{
                override fun onResponse(
                    call: Call<BannerResponse>,
                    response: Response<BannerResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            onSuccess(it.data ?: listOf())
                        }
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<BannerResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            }
        )
    }

    override fun getMovieList(
        status: String?,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getMovieList(status)?.enqueue(
            object :Callback<MovieListResponse>{
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            onSuccess(it.data ?: listOf())
                        }
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            }
        )
    }

    override fun getCinemaList(
        token: String?,
        date: String?,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getCinemaList(token,date)?.enqueue(
            object :Callback<CinemaListResponse>{
                override fun onResponse(
                    call: Call<CinemaListResponse>,
                    response: Response<CinemaListResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.data?.let {
                            onSuccess(it)
                        }
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<CinemaListResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            }
        )
    }

    override fun getCinemaTimeSlotColorList(
        onSuccess: (CinemaTimeSlotColorVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getCinemaTimeSlotColorList()?.enqueue(
            object:Callback<CinemaTimeSlotColorResponse>{
                override fun onResponse(
                    call: Call<CinemaTimeSlotColorResponse>,
                    response: Response<CinemaTimeSlotColorResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.data?.let {
                            onSuccess(it.get(1))
                        }
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<CinemaTimeSlotColorResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            }
        )
    }

    override fun getCinemaSeatPlan(
        token: String?,
        timeSlotId: String?,
        date: String?,
        onSuccess: (List<List<SeatVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getCinemaSeatPlan(token,timeSlotId,date)?.enqueue(
            object :Callback<SeatListResponse>{
                override fun onResponse(
                    call: Call<SeatListResponse>,
                    response: Response<SeatListResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.seatList?.let {
                            onSuccess(it)
                        }
                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<SeatListResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            }
        )
    }

}