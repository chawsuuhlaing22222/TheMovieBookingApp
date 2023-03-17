package com.padc.csh.themovieapplication.delegates

import com.padc.csh.themovieapplication.data.vos.AllCinemaVO
import com.padc.csh.themovieapplication.data.vos.CinemaVO

interface MovieCinemaDelegate{
    fun onMovieCinema(cinemaVO: CinemaVO)
    fun onMovieCinemaAtFrag(cinemaVO: AllCinemaVO)
}