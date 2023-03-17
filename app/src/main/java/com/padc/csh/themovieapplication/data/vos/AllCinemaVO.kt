package com.padc.csh.themovieapplication.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.padc.csh.themovieapplication.persistence.daos.typeconverters.FacilityVOTypeConverter
import com.padc.csh.themovieapplication.persistence.daos.typeconverters.SafetyTypeConverter

@Entity("cinemas")
@TypeConverters(
    FacilityVOTypeConverter::class,
    SafetyTypeConverter::class
)
data class AllCinemaVO(

    @SerializedName("id")
    @ColumnInfo("id")
    @PrimaryKey
    val id: Int?,

    @SerializedName("name")
    @ColumnInfo("name")
    val name: String?,

    @SerializedName("phone")
    @ColumnInfo("phone")
    val phone: String?,

    @SerializedName("email")
    @ColumnInfo("email")
    val email: String?,

    @SerializedName("address")
    @ColumnInfo("address")
    val address: String?,

    @SerializedName("promo_vdo_url")
    @ColumnInfo("promo_vdo_url")
    val promoVdoUrl: String?,

    @SerializedName("facilities")
    @ColumnInfo("facilities")
    val facilities: List<FacilityVO>?,

    @SerializedName("safety")
    @ColumnInfo("safety")
    val safety: List<String>?,


)