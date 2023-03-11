package com.padc.csh.themovieapplication.network.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.padc.csh.themovieapplication.data.vos.CityVO
import com.padc.csh.themovieapplication.persistence.daos.typeconverters.CityListConverter


data class CityResponse(

    @SerializedName("code")
    val code:String?,

    @SerializedName("message")
    val message:String?,

    @SerializedName("data")
    val data:List<CityVO>?

)