package com.padc.csh.themovieapplication.data.vos

import com.google.gson.annotations.SerializedName

data class CinemaVO (

    @SerializedName("cinema_id")
    val id :Int?,

    @SerializedName("cinema")
    val name :String?,

    @SerializedName("timeslots")
    val timeslots :List<CinemaTimeSlotVO>?,

    var isSelected:Boolean?

        )