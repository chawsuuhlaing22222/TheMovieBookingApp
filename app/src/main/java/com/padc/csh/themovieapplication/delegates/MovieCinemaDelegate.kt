package com.padc.csh.themovieapplication.delegates

import com.padc.csh.themovieapplication.data.vos.CinemaVO

interface MovieCinemaDelegate{
    fun onMovieCinema(cinemaVO: CinemaVO)
}