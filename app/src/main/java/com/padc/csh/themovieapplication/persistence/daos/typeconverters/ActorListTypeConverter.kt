package com.padc.csh.themovieapplication.persistence.daos.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.csh.themovieapp.data.vos.ActorVO
import com.padc.csh.themovieapplication.data.vos.CityVO

class ActorListTypeConverter {


    @TypeConverter
    fun toString(cityList: List<ActorVO>?):String{
        return Gson().toJson(cityList)
    }

    @TypeConverter
    fun toActorVO(actorVO: String): List<ActorVO>?{
        var token=object : TypeToken<List<ActorVO>?>() {}.type
        return Gson().fromJson(actorVO,token)
    }
}