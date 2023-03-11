package com.padc.csh.themovieapplication.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("timeslot")
data class TimeSlotColorVO (

    @SerializedName("id")
    @PrimaryKey
    val id:Int?,

    @SerializedName("title")
    @ColumnInfo("title")
    val title:String?,


    @SerializedName("color")
    @ColumnInfo("color")
    val color:String?


        )