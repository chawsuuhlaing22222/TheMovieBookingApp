package com.padc.csh.themovieapplication.network.response

import com.google.gson.annotations.SerializedName
import com.padc.csh.themovieapplication.data.vos.AllCinemaVO
import com.padc.csh.themovieapplication.data.vos.BannerVO

data class AllCinemaResponse(
    @SerializedName("code")
    val code:Int?,

    @SerializedName("message")
    val message:String?,

    @SerializedName("data")
    val data: List<AllCinemaVO>?,
)