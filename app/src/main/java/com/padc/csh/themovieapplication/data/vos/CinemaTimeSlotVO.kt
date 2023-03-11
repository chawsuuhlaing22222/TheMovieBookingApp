package com.padc.csh.themovieapplication.data.vos

import com.google.gson.annotations.SerializedName

data class CinemaTimeSlotVO(

    @SerializedName("cinema_day_timeslot_id")
    val id:Int?,

    @SerializedName("start_time")
    val start_time:String?,

    @SerializedName("status")
    val status:Int?


)