package com.padc.csh.themovieapplication.persistence.daos.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SafetyTypeConverter {

    @TypeConverter
    fun toString(safety: List<String>?):String{
        return Gson().toJson(safety)
    }

    @TypeConverter
    fun toSafetyVO(safety: String): List<String>?{
        var token=object : TypeToken<List<String>?>() {}.type
        return Gson().fromJson(safety,token)
    }
}