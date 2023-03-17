package com.padc.csh.themovieapplication.data.vos

import com.google.gson.annotations.SerializedName

data class FacilityVO (

    @SerializedName("id")
    val id: Int?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("img")
    val img: String?


        )