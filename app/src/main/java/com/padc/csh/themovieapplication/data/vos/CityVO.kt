package com.padc.csh.themovieapplication.data.vos

import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.padc.csh.themovieapplication.persistence.daos.typeconverters.CityListConverter

@Entity(tableName = "city")
data class CityVO(

    @SerializedName("id")
    @PrimaryKey
    val id:Int?,

    @SerializedName("name")
    @ColumnInfo("name")
    val name:String?,

    @SerializedName("created_at")
    val createdAt:String?,

    @SerializedName("updated_at")
    val updatedAt:String?,

    )