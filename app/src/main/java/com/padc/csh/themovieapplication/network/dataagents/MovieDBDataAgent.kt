package com.padc.csh.themovieapplication.network.dataagents

import com.padc.csh.themovieapplication.data.vos.MovieVideoVO

interface MovieDBDataAgent {

    fun getMovieVideo(
        movieId: Int,
        onSuccess:(List<MovieVideoVO>)-> Unit,
        onFailure:(String)->Unit
    )
}