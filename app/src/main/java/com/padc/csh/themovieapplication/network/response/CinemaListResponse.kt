package com.padc.csh.themovieapplication.network.response

import com.google.gson.annotations.SerializedName
import com.padc.csh.themovieapplication.data.vos.CinemaVO
import com.padc.csh.themovieapplication.data.vos.ProfileVO

data class CinemaListResponse (

    @SerializedName("code")
    val code :Int?,

    @SerializedName("message")
    val message:String?,

    @SerializedName("data")
    val data: List<CinemaVO>?,
        )