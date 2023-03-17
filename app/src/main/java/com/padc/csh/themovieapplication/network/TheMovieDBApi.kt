package com.padc.csh.themovieapplication.network


import com.padc.csh.themovieapplication.network.response.MovieVideoResponse
import com.padc.csh.themovieapplication.utils.API_GET_MOVIE_VIDEO
import com.padc.csh.themovieapplication.utils.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBApi {

    @GET("$API_GET_MOVIE_VIDEO")
    fun getMovieVideos(
        @Path("movie_id") movieId:String?,
        @Query("api_key") apiKey:String= API_KEY
    ): Call<MovieVideoResponse>
}