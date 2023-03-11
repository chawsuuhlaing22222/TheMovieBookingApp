package com.padc.csh.themovieapplication.persistence.daos.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.csh.themovieapplication.data.vos.ProfileVO

class ProfileVOTypeConverter {

    @TypeConverter
    fun toString(profileVO: ProfileVO?):String{
        return Gson().toJson(profileVO)
    }

    @TypeConverter
    fun toProfileVO(profileVO: String?):ProfileVO?{
        var token=object : TypeToken<ProfileVO?>() {}.type
        return Gson().fromJson(profileVO,token)
    }
}