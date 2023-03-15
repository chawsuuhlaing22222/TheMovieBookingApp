package com.padc.csh.themovieapplication.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("ticket")
data class TicketVO (

    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = true)
    val id:Int?,

    @ColumnInfo("movie_id")
    var movieId:Int?,

    @ColumnInfo("movie_name")
    var movieName:String?,

    @ColumnInfo("cinema_id")
    var cinemaId:Int?,

    @ColumnInfo("cinema_name")
    var cinemaName:String?,

    @ColumnInfo("start_time")
    var startTime:String?,

    @ColumnInfo("booking_date")
    var bookingDate:String?,


    @ColumnInfo("seat_name")
    var seatNameList:String?,

    @ColumnInfo("seat_count")
    var seatCount:String?,

    @ColumnInfo("qrCode")
    var qrCode:String?,

        )