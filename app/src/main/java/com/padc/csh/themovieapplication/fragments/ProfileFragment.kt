package com.padc.csh.themovieapplication.fragments

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.padc.csh.themovieapplication.R
import com.padc.csh.themovieapplication.activities.LoginChooseActivity
import com.padc.csh.themovieapplication.data.models.MovieBookingModel
import com.padc.csh.themovieapplication.data.models.MovieBookingModelImpl
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.view_item_logout_dialog.view.*


class ProfileFragment : Fragment() {

//model
    private var mTheMovieBookingModel:MovieBookingModel =MovieBookingModelImpl
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpActionListener()
    }

    private fun setUpActionListener(){
        rlLogoutProfileScrn.setOnClickListener {
            var dialog= context?.let { it1 -> Dialog(it1) }
            var view=LayoutInflater.from(context).inflate(R.layout.view_item_logout_dialog,null)
            dialog?.setContentView(view)
            view.btnYes.setOnClickListener {
                mTheMovieBookingModel.deleteProfile()
                var intent=Intent(requireContext(),LoginChooseActivity::class.java)
                intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }

            view.btnNo.setOnClickListener {
                dialog?.dismiss()
            }
            dialog?.show()


        }
    }


}