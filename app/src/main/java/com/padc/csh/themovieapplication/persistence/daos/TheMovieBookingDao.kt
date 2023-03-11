package com.padc.csh.themovieapplication.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapplication.data.vos.CityVO
import com.padc.csh.themovieapplication.data.vos.ProfileVO
import com.padc.csh.themovieapplication.data.vos.TimeSlotColorVO
import com.padc.csh.themovieapplication.network.response.CityResponse

@Dao
interface TheMovieBookingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(movies:List<MovieVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleMovie(movies:MovieVO)

    @Query("SELECT * FROM movies WHERE type= :type")
    fun getMovieListByType(type:String):List<MovieVO>?


    @Query(value = "SELECT * FROM movies")
    fun getAllMovies():List<MovieVO>

    @Query(value = "SELECT * FROM movies WHERE id = :movieId")
    fun getSingleMovie(movieId:Int):MovieVO?


    @Query("DELETE FROM movies")
    fun deleteAllMovies()

    //start
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insetProfileWithToken(profileVO: ProfileVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCityList(cityList:List<CityVO>)

    @Query("SELECT * FROM city")
    fun getCityList():List<CityVO>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTimeSlotColor(timeSlot:List<TimeSlotColorVO>)

    @Query("SELECT * FROM timeslot")
    fun getTimeSlotColor():List<TimeSlotColorVO>


}