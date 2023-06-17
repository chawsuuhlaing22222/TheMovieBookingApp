package com.padc.csh.themovieapplication.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.activities.CheckOutAcitivy
import com.padc.csh.themovieapplication.adapters.TicketItemAdapter
import com.padc.csh.themovieapplication.data.models.MovieBookingModel
import com.padc.csh.themovieapplication.data.models.MovieBookingModelImpl
import com.padc.csh.themovieapplication.delegates.TicketItemDelegate
import kotlinx.android.synthetic.main.fragment_ticket.*


class TicketFragment : Fragment(),TicketItemDelegate {
lateinit var mTicketAdapter:TicketItemAdapter

private var mTheMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setUpRecycler()
        requestData()
    }

    private fun requestData() {
        mTheMovieBookingModel.getAllTickets {
            mTicketAdapter.setNewData(it.sortedByDescending { ticket->ticket.createTicket?.toLong() })
        }
    }

    private fun setUpRecycler() {
       mTicketAdapter= TicketItemAdapter(requireContext(),this)
        rvTicketList.adapter=mTicketAdapter
        rvTicketList.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
    }

    override fun onTapTicketItem() {
    //    startActivity(Intent(CheckOutAcitivy.newIntent(requireContext(),"ticket")))
    }


}