package com.padc.csh.themovieapplication.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapplication.data.vos.*
import com.padc.csh.themovieapplication.network.response.CityResponse

@Dao
interface TheMovieBookingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(movies:List<MovieVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleMovie(movies:MovieVO)

    @Query("SELECT * FROM movies WHERE type= :type")
    fun getMovieListByType(type:String):List<MovieVO>?

    @Query("SELECT * FROM movies WHERE type= :type AND original_title LIKE  '%' || :name || '%'")
    fun searchMovieListByTypeAndName(type:String,name:String):List<MovieVO>?

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

    @Query("SELECT * FROM timeslot WHERE id= :status")
    fun getTimeSlotColorForStatus(status:Int):TimeSlotColorVO?

    @Query("SELECT * FROM profile")
    fun getProfileVO():ProfileVO

    @Query("DELETE FROM profile")
    fun deleteProfile()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTicket(ticketVO: TicketVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCinemaList(cinemaList:List<AllCinemaVO>)

    @Query("SELECT * FROM cinemas")
    fun getAllCinemaList():List<AllCinemaVO>


   // @Insert(onConflict = OnConflictStrategy.REPLACE)
    //fun insertCinemaTimeSlotColorList(timeslotcolorList:Any?)
}