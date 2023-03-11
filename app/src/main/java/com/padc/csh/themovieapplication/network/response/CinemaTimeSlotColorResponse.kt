package com.padc.csh.themovieapplication.network.response

import com.google.gson.annotations.SerializedName
import com.padc.csh.themovieapplication.data.vos.CinemaTimeSlotColorVO
import com.padc.csh.themovieapplication.data.vos.CityVO

class CinemaTimeSlotColorResponse (

    @SerializedName("code")
    val code:String?,

    @SerializedName("message")
    val message:String?,

    @SerializedName("data")
    val data:List<CinemaTimeSlotColorVO>?
        )