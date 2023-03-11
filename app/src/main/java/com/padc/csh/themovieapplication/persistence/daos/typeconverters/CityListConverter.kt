package com.padc.csh.themovieapplication.persistence.daos.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.csh.themovieapplication.data.vos.CityVO
import com.padc.csh.themovieapplication.data.vos.ProfileVO

class CityListConverter {


    @TypeConverter
    fun toString(cityList: List<CityVO>?):String{
        return Gson().toJson(cityList)
    }

    @TypeConverter
    fun toCityVO(cityVo: String): List<CityVO>?{
        var token=object : TypeToken<List<CityVO>?>() {}.type
        return Gson().fromJson(cityVo,token)
    }
}