package com.padc.csh.themovieapplication.persistence.daos.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AnyTypeConverter {
    @TypeConverter
    fun toString(AnyTimeslot: Any?) :String {
        return Gson().toJson(AnyTimeslot)
    }

    @TypeConverter
    fun toAnyTimeSlotVO(jsonString:String) : Any? {
        val configTimeslotAnyType = object : TypeToken<Any?>(){}.type
        return Gson().fromJson(jsonString,configTimeslotAnyType)
    }
}