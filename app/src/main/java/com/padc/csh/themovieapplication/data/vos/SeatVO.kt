package com.padc.csh.themovieapplication.data.vos

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SeatVO (

@SerializedName("id")
val id:Int?,

@SerializedName("type")
var type:String?,

@SerializedName("seat_name")
val seatName:String?,

@SerializedName("symbol")
val symbol:String?,

@SerializedName("price")
val price:Int,

var isSelected:Boolean?


        ):Parcelable{


        }