package com.padc.csh.themovieapplication.delegates

import com.padc.csh.themovieapplication.data.vos.BannerVO

interface BannerDelegate {
    fun onTapBanner(movie:BannerVO)

}