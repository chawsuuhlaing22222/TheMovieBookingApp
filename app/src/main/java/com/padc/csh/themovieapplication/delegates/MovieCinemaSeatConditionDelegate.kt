package com.padc.csh.themovieapplication.delegates

import com.padc.csh.themovieapplication.data.vos.CinemaTimeSlotVO

interface MovieCinemaSeatConditionDelegate {
    fun onMovieCinemaSeatPlanClick(timeSlotVO: CinemaTimeSlotVO)
}