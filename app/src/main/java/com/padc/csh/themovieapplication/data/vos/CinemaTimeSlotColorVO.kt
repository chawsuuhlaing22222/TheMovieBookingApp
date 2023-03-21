package com.padc.csh.themovieapplication.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.padc.csh.themovieapplication.persistence.daos.typeconverters.AnyTypeConverter

//@Entity("cinema_timeslot_color")
//@TypeConverters(
//    AnyTypeConverter::class
//)
data class CinemaTimeSlotColorVO(

    @SerializedName("id")
    val id:Int?,

    @SerializedName("key")
    val key:String?,

    @SerializedName("value")
    val value:Any?,
)