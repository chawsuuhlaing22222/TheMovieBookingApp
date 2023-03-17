package com.padc.csh.themovieapplication.network.response

import com.google.gson.annotations.SerializedName
import com.padc.csh.themovieapplication.data.vos.MovieVideoVO

data class MovieVideoResponse (
    @SerializedName("id")
    val id:Int?,

    @SerializedName("results")
    val results:List<MovieVideoVO>?

        )