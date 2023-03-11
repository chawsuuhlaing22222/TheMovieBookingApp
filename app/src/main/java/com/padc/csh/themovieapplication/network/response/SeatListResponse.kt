package com.padc.csh.themovieapplication.network.response

import com.google.gson.annotations.SerializedName
import com.padc.csh.themovieapplication.data.vos.CityVO
import com.padc.csh.themovieapplication.data.vos.SeatListVO
import com.padc.csh.themovieapplication.data.vos.SeatVO

data class SeatListResponse (

    @SerializedName("code")
    val code:String?,

    @SerializedName("message")
    val message:String?,

    @SerializedName("data")
    val seatList:List<List<SeatVO>>?
        )