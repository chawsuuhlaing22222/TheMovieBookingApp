package com.padc.csh.themovieapplication.delegates

import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapplication.data.vos.SeatVO

interface ChildSeatDelegate {
    fun onSelectdSeat(seatVO: SeatVO)
    fun onUnSelectdSeat(seatVO: SeatVO)
}