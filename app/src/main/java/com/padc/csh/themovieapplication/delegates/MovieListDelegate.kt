package com.padc.csh.themovieapplication.delegates

interface MovieListDelegate {
    fun onTapNowShowingMovie(movieId:String)
    fun onTapCommingSoonMovie(movieId: String)
}