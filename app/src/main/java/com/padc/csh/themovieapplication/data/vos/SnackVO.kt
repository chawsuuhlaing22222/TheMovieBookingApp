package com.padc.csh.themovieapplication.data.vos

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SnackVO (
    @SerializedName("id")
    val id:Int?,

    @SerializedName("name")
    val name:String?,

    @SerializedName("description")
    val description:String?,

    @SerializedName("price")
    val price:Int,

    @SerializedName("category_id")
    val categoryId:Int?,

    @SerializedName("image")
    val image:String?,

    var count:Int

        ):Parcelable