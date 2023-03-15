package com.padc.csh.themovieapplication.network.response

import com.google.gson.annotations.SerializedName
import com.padc.csh.themovieapplication.data.vos.BannerVO
import com.padc.csh.themovieapplication.data.vos.CheckOutResponseVO

data class CheckOutResponse (
    @SerializedName("code")
    val code:Int?,

    @SerializedName("message")
    val message:String?,

    @SerializedName("data")
    val data: CheckOutResponseVO?,
        )