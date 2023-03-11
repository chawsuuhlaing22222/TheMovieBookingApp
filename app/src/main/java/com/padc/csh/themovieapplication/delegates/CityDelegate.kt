package com.padc.csh.themovieapplication.delegates

import com.padc.csh.themovieapplication.data.vos.CityVO

interface CityDelegate {
    fun onCityItemClick(cityVO: CityVO)

}