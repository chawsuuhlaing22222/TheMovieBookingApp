package com.padc.csh.themovieapplication.data.vos

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class CheckOutResponseVO(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("booking_no")
    val bookingNo: String?,

    @SerializedName("booking_date")
    val bookingDate: String?,

    @SerializedName("row")
    val row: String?,

    @SerializedName("seat")
    val seat: String?,

    @SerializedName("total_seat")
    val totalSeat: Int?,

    @SerializedName("total")
    val total: String?,

    @SerializedName("movie_id")
    val movieId: Int?,

    @SerializedName("cinema_id")
    val cinemaId: Int?,

    @SerializedName("username")
    val username: String?,

    @SerializedName("timeslot")
    val timeslot: CinemaTimeSlotVO?,

    @SerializedName("snacks")
    val snacks: List<SnackVO>?,

    @SerializedName("qr_code")
    val qrCode: String?

)