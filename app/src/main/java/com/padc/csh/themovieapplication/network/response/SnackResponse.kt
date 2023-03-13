package com.padc.csh.themovieapplication.network.response

import com.google.gson.annotations.SerializedName
import com.padc.csh.themovieapplication.data.vos.SnackCategoryVO
import com.padc.csh.themovieapplication.data.vos.SnackVO

data class SnackResponse (
    @SerializedName("code")
    val code:String?,

    @SerializedName("message")
    val message:String?,

    @SerializedName("data")
    val data:List<SnackVO>?
        )