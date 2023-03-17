package com.padc.csh.themovieapplication.network.dataagents

import com.padc.csh.themovieapplication.data.vos.MovieVideoVO
import com.padc.csh.themovieapplication.network.TheMovieDBApi
import com.padc.csh.themovieapplication.network.response.MovieVideoResponse
import com.padc.csh.themovieapplication.utils.BASE_URL_MOVIE_DB
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

 object MovieDBDataAgentImpl:MovieDBDataAgent {

    var mTheMovieDBApi: TheMovieDBApi?=null
    init {
        val okHttpClient= OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL_MOVIE_DB)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mTheMovieDBApi=retrofit.create(TheMovieDBApi::class.java)
    }

    override fun getMovieVideo(
        movieId: Int,
        onSuccess: (List<MovieVideoVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieDBApi?.getMovieVideos(movieId.toString())?.enqueue(
            object :Callback<MovieVideoResponse>{
                override fun onResponse(
                    call: Call<MovieVideoResponse>,
                    response: Response<MovieVideoResponse>
                ) {
                    if(response.isSuccessful){

                        response.body()?.results?.let {
                            onSuccess(it)
                        }

                    }else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<MovieVideoResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            }
        )
    }
}