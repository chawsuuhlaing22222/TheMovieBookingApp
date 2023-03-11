package com.padc.csh.themovieapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.padc.csh.themovieapp.persistence.typeconverters.*
import com.padc.csh.themovieapplication.persistence.daos.typeconverters.ActorListTypeConverter

@Entity(tableName = "movies")
@TypeConverters(
    GenreIdIdListTypeConverter::class,
    ActorListTypeConverter::class
)
data class MovieVO(


    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int?,

    @SerializedName("original_title")
    @ColumnInfo(name = "original_title")
    val originalTtitle: String?,

    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,

    @SerializedName("genres")
    @ColumnInfo(name = "genres")
    val genres: List<String>?,


    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    val overview: String?,

    @SerializedName("rating")
    @ColumnInfo(name = "rating")
    val rating:Double?,

    @SerializedName("runtime")
    @ColumnInfo(name = "runtime")
    val runtime:Int?,

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @SerializedName("casts")
    @ColumnInfo(name = "casts")
    val casts:List<ActorVO>?,

    @ColumnInfo(name = "type")
    var type: String?

) {


    fun getGenresAsCommaSeparatedString(): String {
        var v = genres?.joinToString(",") ?: ""
        return v
    }
}

const val NOW_PLAYING = "current"
const val COMMING_SOON = "comingsoon"

