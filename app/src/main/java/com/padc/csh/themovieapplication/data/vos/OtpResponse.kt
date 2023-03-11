package com.padc.csh.themovieapplication.data.vos

import com.google.gson.annotations.SerializedName

data class OtpVO(
    @SerializedName("code")
    val code:String?,

    @SerializedName("message")
    val message:String?
)
