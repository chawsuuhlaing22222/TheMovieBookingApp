package com.padc.csh.themovieapplication.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapplication.data.vos.CityVO
import com.padc.csh.themovieapplication.data.vos.ProfileVO
import com.padc.csh.themovieapplication.data.vos.TicketVO
import com.padc.csh.themovieapplication.data.vos.TimeSlotColorVO
import com.padc.csh.themovieapplication.network.response.CheckOTPResponse
import com.padc.csh.themovieapplication.network.response.CityResponse
import com.padc.csh.themovieapplication.persistence.daos.TheMovieBookingDao

@Database(entities = [MovieVO::class, CityVO::class, ProfileVO::class, TimeSlotColorVO::class,TicketVO::class], version = 7, exportSchema = false)
abstract class TheMovieBookingDatabase: RoomDatabase() {

    companion object{
        const val DB_NAME="THE_MOVIE_BOOKING_DB"
        var movieBookingDatabase:TheMovieBookingDatabase?=null
        fun getTheMovieBookingDbInstance(context: Context):TheMovieBookingDatabase?{
            when(movieBookingDatabase){
                null->{
                    movieBookingDatabase=Room.databaseBuilder(context, TheMovieBookingDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return movieBookingDatabase
        }

    }

    abstract fun movieBookingDao():TheMovieBookingDao
}