package com.padc.csh.themovieapplication.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("profile")
data class ProfileVO(
    @SerializedName("id")
    @PrimaryKey
    val id:Int?,

    @SerializedName("name")
    @ColumnInfo("name")
    val name:String?,

    @SerializedName("email")
    @ColumnInfo("email")
    val email:String?,

    @SerializedName("phone")
    @ColumnInfo("phone")
    val phone:String?,

    @SerializedName("total_expense")
    @ColumnInfo("total_expense")
    val totalExpense:Long?,

    @SerializedName("profile_image")
    @ColumnInfo("profile_image")
    val profileImage:String?,

    @ColumnInfo("token")
    var token:String?

)