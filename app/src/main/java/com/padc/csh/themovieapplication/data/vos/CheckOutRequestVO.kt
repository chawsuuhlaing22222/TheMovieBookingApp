package com.padc.csh.themovieapplication.data.vos

import com.google.gson.annotations.SerializedName


data class CheckOutRequestVO (
    @SerializedName("cinema_day_timeslot_id")
    val timeSlotId:Int?,

    @SerializedName("seat_number")
    val seatNumber:String?,

    @SerializedName("booking_date")
    val bookingDate:String?,

    @SerializedName("movie_id")
    val movieId:Long?,

    @SerializedName("payment_type_id")
    val paymentTypeId:Int?,

    @SerializedName("snacks")
    val snacks:List<CheckOutSnackVO>?,


        )

