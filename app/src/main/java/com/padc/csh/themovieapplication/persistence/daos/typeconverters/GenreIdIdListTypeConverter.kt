package com.padc.csh.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdIdListTypeConverter {

    @TypeConverter
    fun toString(genreIdList:List<String>?):String{
        return Gson().toJson(genreIdList)
    }

    @TypeConverter
    fun toGenreIdList(genreIdList: String):List<String>?{
        var toType=object :TypeToken<List<String>?>(){}.type
        return Gson().fromJson(genreIdList,toType)
    }
}