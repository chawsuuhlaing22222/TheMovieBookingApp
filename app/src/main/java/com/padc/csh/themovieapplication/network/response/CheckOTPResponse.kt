package com.padc.csh.themovieapplication.network.response

import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.padc.csh.themovieapplication.data.vos.ProfileVO
import com.padc.csh.themovieapplication.persistence.daos.typeconverters.ProfileVOTypeConverter


data class CheckOTPResponse (


    @SerializedName("code")
    val code:Int?,

    @SerializedName("message")
    val message:String?,

    @SerializedName("data")
    val data:ProfileVO?,

    @SerializedName("token")
    val token:String?

    ){

    fun isSuccessful():Boolean{
        return message=="Success"
    }
}
