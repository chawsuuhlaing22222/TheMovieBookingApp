package com.padc.csh.themovieapplication.dummy

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.util.Calendar
import java.util.Date



//val movieGenreList= listOf<String>("Action","Adventure","Animation","Drama
@RequiresApi(Build.VERSION_CODES.O)
val dateList= listOf<LocalDate>(LocalDate.now(),LocalDate.now().plusDays(1),LocalDate.now().plusDays(2),LocalDate.now().plusDays(4))