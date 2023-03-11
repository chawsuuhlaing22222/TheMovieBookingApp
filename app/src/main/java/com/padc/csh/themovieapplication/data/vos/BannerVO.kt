package com.padc.csh.themovieapplication.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("banner")
data class BannerVO (

    @SerializedName("id")
    @PrimaryKey
    val id:Int?,

    @SerializedName("title")
    val title:String?,

    @SerializedName("url")
    val url:String?,

    @SerializedName("is_active")
    val isActive:Int?,

    @SerializedName("created_at")
    val createdAt:String?,

    @SerializedName("updated_at")
    val updatedAt:String?,

        )