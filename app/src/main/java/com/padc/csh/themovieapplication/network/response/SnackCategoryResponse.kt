package com.padc.csh.themovieapplication.network.response

import com.google.gson.annotations.SerializedName
import com.padc.csh.themovieapplication.data.vos.SeatVO
import com.padc.csh.themovieapplication.data.vos.SnackCategoryVO

data class SnackCategoryResponse (
        @SerializedName("code")
        val code:String?,

        @SerializedName("message")
        val message:String?,

        @SerializedName("data")
        val data:List<SnackCategoryVO>?
        )