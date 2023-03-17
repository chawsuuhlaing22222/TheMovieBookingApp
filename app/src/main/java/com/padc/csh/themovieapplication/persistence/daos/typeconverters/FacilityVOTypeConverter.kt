package com.padc.csh.themovieapplication.persistence.daos.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.csh.themovieapplication.data.vos.AllCinemaVO
import com.padc.csh.themovieapplication.data.vos.FacilityVO

class FacilityVOTypeConverter {

    @TypeConverter
    fun toString(facilityList: List<FacilityVO>?):String{
        return Gson().toJson(facilityList)
    }

    @TypeConverter
    fun toFacilityVO(facilityList: String): List<FacilityVO>?{
        var token=object : TypeToken<List<FacilityVO>?>() {}.type
        return Gson().fromJson(facilityList,token)
    }
}