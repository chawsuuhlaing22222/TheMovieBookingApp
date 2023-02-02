package com.padc.csh.themovieapplication.dummy

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.util.Calendar
import java.util.Date

val cityList= listOf<String>("Yangon","Mandalay","NaypyiDaw","Monywa")

val snackList= listOf<String>("All","Combo","Snack","Pop Corn","Beverage")

var orderedFoodList= mutableListOf<String>("Combo","Snack","Pop Corn","Beverage")

var paymentTypeList= mutableListOf<String>("UPI","Gift Voucher","Quick Pay","Credit Card / Debit Card","Redeem Point",
"Mobile Wallet","Net Banking")

var movieGenreList= mutableListOf<String>("Action","Drama","Science")
var movieFormats= mutableListOf<String>("MP4","MP3","MVC")
var movieShowMonths= mutableListOf<String>("Jan","Feb","Mar","Apri")

var seatPlanlist= listOf<SeatPlan>(
    SeatPlan(
    "Normal (4500ks)",
       ChildSeat(listOf("A","one","one","one","one","space","space","one","one","one","one","A",
           "B","one","one","one","one","space","space","one","one","one","one","B")
       )
    ),
    SeatPlan(
        "Executive (6500ks))",
        ChildSeat(listOf("C","one","one","one","one","space","space","one","one","one","one","C",
            "D","one","one","one","one","space","space","one","one","one","one","D")
        )
    ),
    SeatPlan(
        "Premium (8500ks)",
        ChildSeat(listOf("E","one","one","one","one","space","space","one","one","one","one","E",
            "F","one","one","one","one","space","space","one","one","one","one","F")
        )
    ),

    SeatPlan(
        "Gold (10000ks))",
        ChildSeat(listOf("G","two","space","one","one","one","one","one","space","two","G",
            "H","two","space","one","one","one","one","one","space","two","G")
        )
    ),


)

