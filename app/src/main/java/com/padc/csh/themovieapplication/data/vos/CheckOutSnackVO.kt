package com.padc.csh.themovieapplication.data.vos

import com.google.gson.annotations.SerializedName

data class CheckOutSnackVO (
    @SerializedName("id")
    val id: Int?,

    @SerializedName("quantity")
    val quantity: Int

        )