package com.padc.csh.themovieapplication.data.vos

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class CinemaTimeSlotColorVO(

    @SerializedName("id")
    val id:Int?,

    @SerializedName("key")
    val key:String?,

    @SerializedName("value")
    val value:Any,
)